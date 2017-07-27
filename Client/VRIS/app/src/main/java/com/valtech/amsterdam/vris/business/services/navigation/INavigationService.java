package com.valtech.amsterdam.vris.business.services.navigation;

import com.valtech.amsterdam.recyclist.modifiers.Updater;
import com.valtech.amsterdam.vris.model.ITimeSlot;
import com.valtech.amsterdam.vris.ui.BaseActivity;

/**
 * Created by marvin.brouwer on 27-7-2017.
 */

public interface INavigationService {
    void setCurrentActivity(BaseActivity activity);
    void setTimeSlotUpdater(Updater<ITimeSlot> timeSlotUpdater);
    void navigateToHomeSlot();
    void forceNavigateToHomeSlot();
    void navigateToTimeSlot(ITimeSlot timeSlot);
}
