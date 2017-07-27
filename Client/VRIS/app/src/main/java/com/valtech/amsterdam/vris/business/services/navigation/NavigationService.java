package com.valtech.amsterdam.vris.business.services.navigation;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.valtech.amsterdam.vris.R;
import com.valtech.amsterdam.vris.business.factories.TimeSlotDetailFragmentFactory;
import com.valtech.amsterdam.vris.business.loaders.ITimeSlotLoader;
import com.valtech.amsterdam.vris.model.ITimeSlot;
import com.valtech.amsterdam.vris.ui.BaseActivity;

import org.joda.time.DateTime;

/**
 * Created by marvin.brouwer on 27-7-2017.
 */

public final class NavigationService implements INavigationService {

    @Nullable
    private Fragment previousFragment;
    private final ITimeSlotLoader timeSlotLoader;
    private final TimeSlotDetailFragmentFactory timeSlotDetailFragmentFactory;
    private BaseActivity currentActivity;
    private boolean isHome;

    public NavigationService(ITimeSlotLoader timeSlotLoader, TimeSlotDetailFragmentFactory timeSlotDetailFragmentFactory) {
        this.timeSlotLoader = timeSlotLoader;
        this.timeSlotDetailFragmentFactory = timeSlotDetailFragmentFactory;
    }

    public void setCurrentActivity(BaseActivity activity){
        currentActivity = activity;
    }

    public void navigateToHomeSlot() {
        if(isHome) return;

        ITimeSlot currentTimeSlot = timeSlotLoader.getByTime(DateTime.now().toLocalDateTime());
        Fragment fragment = timeSlotDetailFragmentFactory.getDetail(currentTimeSlot);
        navigateToFragment(fragment, false);
        isHome = true;
    }

    @Override
    public void clearHistory() {
        previousFragment = null;
    }

    public void navigateToPreviousOrHome() {
        if(previousFragment != null) navigateToFragment(previousFragment, false);
        else navigateToHomeSlot();

        clearHistory();
    }

    public void navigateToTimeSlot(ITimeSlot timeSlot) {
        Fragment fragment = timeSlotDetailFragmentFactory.getDetailOrCreate(timeSlot);
        navigateToFragment(fragment, true);
    }


    public void navigateToFragment(Fragment fragment, boolean addToHistory) {
        isHome = false;

        FragmentManager fragmentManager = currentActivity.getSupportFragmentManager();

        // Assuming there's always one fragment in view
        if(addToHistory) previousFragment = fragmentManager.getFragments().get(0);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        fragmentTransaction.replace(R.id.reservation_detail_container, fragment);

        fragmentTransaction.commitAllowingStateLoss();
    }
}
