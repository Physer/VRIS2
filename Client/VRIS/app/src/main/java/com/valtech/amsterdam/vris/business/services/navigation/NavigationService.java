package com.valtech.amsterdam.vris.business.services.navigation;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.valtech.amsterdam.recyclist.modifiers.Updater;
import com.valtech.amsterdam.vris.R;
import com.valtech.amsterdam.vris.business.factories.TimeSlotDetailFragmentFactory;
import com.valtech.amsterdam.vris.model.ITimeSlot;
import com.valtech.amsterdam.vris.ui.BaseActivity;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

import java.util.List;

/**
 * Created by marvin.brouwer on 27-7-2017.
 */

public final class NavigationService implements INavigationService {

    private Updater<ITimeSlot> mTimeSlotUpdater;
    private final TimeSlotDetailFragmentFactory fTimeSlotDetailFragmentFactory;
    private BaseActivity mCurrentActivity;
    private boolean mIsHome;

    public NavigationService(TimeSlotDetailFragmentFactory timeSlotDetailFragmentFactory) {
        fTimeSlotDetailFragmentFactory = timeSlotDetailFragmentFactory;
    }

    public void setTimeSlotUpdater(Updater<ITimeSlot> timeSlotUpdater){
        this.mTimeSlotUpdater = timeSlotUpdater;
    }
    // todo extract away find out another way to centralize getting a single timeslot
    public Updater<ITimeSlot> getTimeSlotUpdater(){
        return this.mTimeSlotUpdater;
    }

    public void setCurrentActivity(BaseActivity activity){
        mCurrentActivity = activity;
    }

    private ITimeSlot getNowTimeSlot(){
        CheckSetup();
        LocalDateTime date = DateTime.now().toLocalDateTime();
        List<ITimeSlot> timeSlots = mTimeSlotUpdater.getList();

        for (ITimeSlot timeSlot: timeSlots) {
            LocalDateTime startDate = timeSlot.getStart();
            LocalDateTime endDate = timeSlot.getEnd();

            if(date.isBefore(startDate)) continue;
            if(date.isAfter(endDate)) continue;

            return timeSlot;
        }

        // shouldn't happen
        return timeSlots.get(0);
    }

    public void navigateToHomeSlot() {
        CheckSetup();
        if(mIsHome) return;
        forceNavigateToHomeSlot(true);
    }
    public void forceNavigateToHomeSlot(boolean hideKeyboard) {
        CheckSetup();
        ITimeSlot currentTimeSlot = getNowTimeSlot();
        Fragment fragment = fTimeSlotDetailFragmentFactory.getDetail(currentTimeSlot, hideKeyboard);
        navigateToFragment(fragment);
        selectTimeSlot(currentTimeSlot.getId());
        mIsHome = true;
    }

    public void navigateToTimeSlot(ITimeSlot timeSlot) {
        CheckSetup();
        Fragment fragment = fTimeSlotDetailFragmentFactory.getDetail(timeSlot, false);
        navigateToFragment(fragment);
        selectTimeSlot(timeSlot.getId());
    }

    private void selectTimeSlot(int timeSlotId) {
        CheckSetup();
        for (ITimeSlot timeSlotItem: mTimeSlotUpdater.getList()) {
            if(timeSlotItem.getId() == timeSlotId) timeSlotItem.setSelected(true);
            else if(timeSlotItem.getSelected() == true) {
                timeSlotItem.setSelected(false);
            } else continue;

            mTimeSlotUpdater.update(timeSlotItem);
        }

        mTimeSlotUpdater.notifyItemUpdated();
    }

    private void navigateToFragment(Fragment fragment) {
        CheckSetup();
        // Close keyboard on navigate
        HideKeyboard();

        FragmentManager fragmentManager = mCurrentActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        fragmentTransaction.replace(R.id.reservation_detail_container, fragment);

        fragmentTransaction.commitAllowingStateLoss();
        mIsHome = false;
    }

    private void HideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager)mCurrentActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        View focusView = mCurrentActivity.getCurrentFocus();
        if(focusView != null) inputMethodManager.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
    }

    private void CheckSetup() {
        if(mCurrentActivity == null) throw new IllegalStateException("Please register the current Activity before using this service");
        if(mTimeSlotUpdater == null) throw new IllegalStateException("Please register the TimeSLotUpdater before using this service");
    }
}
