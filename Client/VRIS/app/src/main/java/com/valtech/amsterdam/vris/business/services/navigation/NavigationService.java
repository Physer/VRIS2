package com.valtech.amsterdam.vris.business.services.navigation;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.valtech.amsterdam.recyclist.modifiers.Updater;
import com.valtech.amsterdam.vris.R;
import com.valtech.amsterdam.vris.business.factories.TimeSlotDetailFragmentFactory;
import com.valtech.amsterdam.vris.model.ITimeSlot;
import com.valtech.amsterdam.vris.ui.BaseActivity;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marvin.brouwer on 27-7-2017.
 */

public final class NavigationService implements INavigationService {

    private List<Fragment> previousFragments;
    private Updater<ITimeSlot> timeSlotUpdater;
    private final TimeSlotDetailFragmentFactory timeSlotDetailFragmentFactory;
    private BaseActivity currentActivity;
    private boolean isHome;

    public NavigationService(TimeSlotDetailFragmentFactory timeSlotDetailFragmentFactory) {
        this.timeSlotDetailFragmentFactory = timeSlotDetailFragmentFactory;
        previousFragments = new ArrayList<>();
    }

    public void setTimeSlotUpdater(Updater<ITimeSlot> timeSlotUpdater){
        this.timeSlotUpdater = timeSlotUpdater;
    }
    // todo extract away find out another way to centralize getting a single timeslot
    public Updater<ITimeSlot> getTimeSlotUpdater(){
        return this.timeSlotUpdater;
    }


    public void setCurrentActivity(BaseActivity activity){
        currentActivity = activity;
    }

    private ITimeSlot getNowTimeSlot(){
        LocalDateTime date = DateTime.now().toLocalDateTime();
        List<ITimeSlot> timeSlots = timeSlotUpdater.getList();

        for (ITimeSlot timeSlot: timeSlots) {
            LocalDateTime startDate = timeSlot.getStartDate();
            LocalDateTime endDate = timeSlot.getEndDate();

            if(date.isBefore(startDate)) continue;
            if(date.isAfter(endDate)) continue;

            return timeSlot;
        }

        // shouldn't happen
        return timeSlots.get(0);
    }

    public void navigateToHomeSlot() {
        if(isHome) return;

        ITimeSlot currentTimeSlot = getNowTimeSlot();
        Fragment fragment = timeSlotDetailFragmentFactory.getDetail(currentTimeSlot);
        navigateToFragment(fragment);
        selectTimeSlot(currentTimeSlot.getId());
        isHome = true;
    }

    @Override
    public void clearHistory() {
        previousFragments.clear();
    }

    public Fragment getPrevious(boolean popFromList) {
        if(previousFragments.size() == 0) return  null;

        Fragment topHistoryFragment = previousFragments.get(previousFragments.size() -1);
        if(popFromList) previousFragments.remove(topHistoryFragment);
        return topHistoryFragment;
    }

    public void navigateToPreviousOrHome() {
        Fragment previousFragment = getPrevious(true);
        if(previousFragment != null) navigateToFragment(previousFragment);
        else navigateToHomeSlot();

        clearHistory();
    }

    public void navigateToTimeSlot(ITimeSlot timeSlot) {
        Fragment fragment = timeSlotDetailFragmentFactory.getDetailOrCreate(timeSlot);
        navigateToFragment(fragment);
        selectTimeSlot(timeSlot.getId());
    }

    private void selectTimeSlot(int timeSlotId) {
        for (ITimeSlot timeSlotItem: timeSlotUpdater.getList()) {
            if(timeSlotItem.getId() == timeSlotId) timeSlotItem.setSelected(true);
            else if(timeSlotItem.getSelected() == true) {
                timeSlotItem.setSelected(false);
            } else continue;

            timeSlotUpdater.update(timeSlotItem);
        }

        timeSlotUpdater.notifyItemUpdated();
    }


    public void navigateToFragment(Fragment fragment) {
        isHome = false;

        FragmentManager fragmentManager = currentActivity.getSupportFragmentManager();

        // Assuming there's always one fragment in view
        List<Fragment> fragments = fragmentManager.getFragments();
        if(fragments != null) previousFragments.add(fragmentManager.getFragments().get(0));

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        fragmentTransaction.replace(R.id.reservation_detail_container, fragment);

        fragmentTransaction.commitAllowingStateLoss();
    }
}
