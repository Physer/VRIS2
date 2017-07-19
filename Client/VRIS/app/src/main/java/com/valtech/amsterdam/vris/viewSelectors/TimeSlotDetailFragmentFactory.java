package com.valtech.amsterdam.vris.viewSelectors;

import android.app.Fragment;
import android.os.Bundle;

import com.valtech.amsterdam.recyclist.ViewSelector;
import com.valtech.amsterdam.recyclist.loader.ModelLoader;
import com.valtech.amsterdam.vris.R;
import com.valtech.amsterdam.vris.model.ITimeSlot;
import com.valtech.amsterdam.vris.model.Reservation;
import com.valtech.amsterdam.vris.model.TimeSlot;
import com.valtech.amsterdam.vris.ui.ReservationDetailFragment;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by jasper.van.zijp on 18-7-2017.
 */

public class TimeSlotDetailFragmentFactory {

    private ModelLoader<ITimeSlot> timeSlotLoader;

    public TimeSlotDetailFragmentFactory(ModelLoader<ITimeSlot> timeSlotLoader) {
        this.timeSlotLoader = timeSlotLoader;
    }

    public ReservationDetailFragment getByTimeSlot(ITimeSlot timeSlot) throws IOException {

        // Possible loop (shouldn't tho)
        if(timeSlot == null) return getNow();

        ReservationDetailFragment fragment = timeSlot instanceof Reservation ?
            new ReservationDetailFragment() :
            new ReservationDetailFragment();

        Bundle arguments = new Bundle();
        arguments.putInt(ReservationDetailFragment.ARG_ITEM_ID, timeSlot.getId());
        fragment.setArguments(arguments);

        return fragment;
    }

    public ReservationDetailFragment getByTime(Date date)
        throws IOException, IndexOutOfBoundsException {

        List<ITimeSlot> timeSlots = timeSlotLoader.getList();
        for (ITimeSlot timeSlot: timeSlots) {
            if(date.before(timeSlot.getStart())) continue;
            if(date.after(timeSlot.getEnd())) continue;

            return getByTimeSlot(timeSlot);
        }

        throw new IndexOutOfBoundsException();
    }

    public ReservationDetailFragment getNow()
            throws IOException {
        return getByTime(new Date());
    }
}
