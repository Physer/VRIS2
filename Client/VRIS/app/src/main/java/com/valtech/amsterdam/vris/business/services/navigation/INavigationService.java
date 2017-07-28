package com.valtech.amsterdam.vris.business.services.navigation;

import com.valtech.amsterdam.recyclist.modifiers.Updater;
import com.valtech.amsterdam.vris.model.ITimeSlot;
import com.valtech.amsterdam.vris.ui.BaseActivity;

/**
 * Created by marvin.brouwer on 27-7-2017.
 */

/**
 * Service responsible for navigation state
 */
public interface INavigationService {
    /**
     * Set the activity that uses this service at the moment
     * @param activity
     */
    void setCurrentActivity(BaseActivity activity);

    /**
     * Set the Updater responsible for selecting the active timeslot
     * @param timeSlotUpdater
     */
    void setTimeSlotUpdater(Updater<ITimeSlot> timeSlotUpdater);

    /**
     * Navigate the the timeslot that is NOW (does nothing if already there)
     */
    void navigateToHomeSlot();
    /**
     * Navigate the the timeslot that is NOW regardless if it's already loaded
     */
    void forceNavigateToHomeSlot();

    /**
     * Navigate to a timeslot, show reservation if it is one, else show create view
     * @param timeSlot
     */
    void navigateToTimeSlot(ITimeSlot timeSlot);
}
