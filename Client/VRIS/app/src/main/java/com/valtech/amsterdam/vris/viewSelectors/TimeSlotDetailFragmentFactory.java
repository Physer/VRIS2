package com.valtech.amsterdam.vris.viewSelectors;

import android.os.Bundle;

import com.valtech.amsterdam.recyclist.loader.ModelLoader;
import com.valtech.amsterdam.vris.model.ITimeSlot;
import com.valtech.amsterdam.vris.model.Reservation;
import com.valtech.amsterdam.vris.ui.ReservationDetailFragment;
import com.valtech.amsterdam.vris.ui.TimeSlotDetailFragment;

import org.joda.time.DateTime;

import java.io.IOException;

import java.util.List;

/**
 * Created by jasper.van.zijp on 18-7-2017.
 */

public class TimeSlotDetailFragmentFactory {

    private ModelLoader<ITimeSlot> timeSlotLoader;

    public TimeSlotDetailFragmentFactory(ModelLoader<ITimeSlot> timeSlotLoader) {
        this.timeSlotLoader = timeSlotLoader;
    }

    public TimeSlotDetailFragment getByTimeSlot(ITimeSlot timeSlot) {

        // Possible loop (shouldn't tho)
        if(timeSlot == null) return getNow();

        TimeSlotDetailFragment fragment = timeSlot instanceof Reservation ?
            new ReservationDetailFragment() :
            new TimeSlotDetailFragment();

        Bundle arguments = new Bundle();
        arguments.putInt(ReservationDetailFragment.ARG_ITEM_ID, timeSlot.getId());
        fragment.setArguments(arguments);

        return fragment;
    }

    public TimeSlotDetailFragment getByTime(DateTime date) throws IndexOutOfBoundsException {

        List<ITimeSlot> timeSlots = null;
        try {
            timeSlots = timeSlotLoader.getList();
        } catch (IOException e) {
            e.printStackTrace();
            return getNow();
        }
        for (ITimeSlot timeSlot: timeSlots) {
            DateTime startDate = timeSlot.getStartDate();
            DateTime endDate = timeSlot.getEndDate();

            if(date.isBefore(startDate)) continue;
            if(date.isAfter(endDate)) continue;

            return getByTimeSlot(timeSlot);
        }

        throw new IndexOutOfBoundsException();
    }

    public TimeSlotDetailFragment getById(int id) throws IndexOutOfBoundsException {

        if(id == -1) return getNow();

        List<ITimeSlot> timeSlots = null;
        try {
            timeSlots = timeSlotLoader.getList();
        } catch (IOException e) {
            e.printStackTrace();
            return getNow();
        }
        for (ITimeSlot timeSlot: timeSlots) {
            if(id != timeSlot.getId()) continue;
            return getByTimeSlot(timeSlot);
        }

        throw new IndexOutOfBoundsException();
    }

    public TimeSlotDetailFragment getNow() {
        return getByTime(DateTime.now());
    }
}
