package com.valtech.amsterdam.vris.business.factories;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.valtech.amsterdam.vris.model.ITimeSlot;
import com.valtech.amsterdam.vris.model.Reservation;
import com.valtech.amsterdam.vris.ui.NewTimeSlotFragment;
import com.valtech.amsterdam.vris.ui.ReservationDetailFragment;
import com.valtech.amsterdam.vris.ui.TimeSlotDetailFragment;

/**
 * Created by jasper.van.zijp on 18-7-2017.
 */

public class TimeSlotDetailFragmentFactory {

    public Fragment getDetail(ITimeSlot timeSlot) {

        if(timeSlot == null) return null; // todo error view

        Fragment fragment = timeSlot instanceof Reservation ?
                new ReservationDetailFragment() :
                new TimeSlotDetailFragment();

        Bundle arguments = new Bundle();
        arguments.putInt(ReservationDetailFragment.ARG_ITEM_ID, timeSlot.getId());
        fragment.setArguments(arguments);

        return fragment;
    }

    public Fragment getDetailOrCreate(ITimeSlot timeSlot) {

        if(timeSlot == null) return null; // todo error view

        Fragment fragment = timeSlot instanceof Reservation ?
                new ReservationDetailFragment() :
                new NewTimeSlotFragment();

        Bundle arguments = new Bundle();
        arguments.putInt(ReservationDetailFragment.ARG_ITEM_ID, timeSlot.getId());
        fragment.setArguments(arguments);

        return fragment;
    }
}
