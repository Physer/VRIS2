package com.valtech.amsterdam.vris.business.services.navigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.valtech.amsterdam.vris.R;
import com.valtech.amsterdam.vris.model.ITimeSlot;
import com.valtech.amsterdam.vris.ui.BaseActivity;

/**
 * Created by marvin.brouwer on 27-7-2017.
 */

public interface INavigationService {
    void setCurrentActivity(BaseActivity activity);
    void navigateToHomeSlot();
    void clearHistory();
    void navigateToPreviousOrHome();
    void navigateToTimeSlot(ITimeSlot timeSlot);
    void navigateToFragment(Fragment fragment, boolean addToHistory);
}
