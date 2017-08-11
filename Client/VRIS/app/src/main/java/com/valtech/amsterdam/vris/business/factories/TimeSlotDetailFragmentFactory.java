package com.valtech.amsterdam.vris.business.factories;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.valtech.amsterdam.vris.model.ITimeSlot;
import com.valtech.amsterdam.vris.model.Reservation;
import com.valtech.amsterdam.vris.ui.NewTimeSlotFragment;
import com.valtech.amsterdam.vris.ui.ReservationDetailFragment;

/**
 * Created by jasper.van.zijp on 18-7-2017.
 */

public class TimeSlotDetailFragmentFactory {

    public Fragment getDetail(ITimeSlot timeSlot, boolean hideKeyboard) {

        if(timeSlot == null) return null; // todo error view

        Fragment fragment = timeSlot instanceof Reservation ?
                new ReservationDetailFragment() :
                new NewTimeSlotFragment();

        Bundle arguments = new Bundle();
        arguments.putInt(ReservationDetailFragment.ARG_ITEM_ID, timeSlot.getId());
        arguments.putInt(ReservationDetailFragment.ARG_HIDE_KEYBOARD, hideKeyboard ? 0 : 1);
        fragment.setArguments(arguments);

        return fragment;
    }
}
