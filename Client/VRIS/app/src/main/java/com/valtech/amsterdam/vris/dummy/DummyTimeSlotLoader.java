package com.valtech.amsterdam.vris.dummy;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.valtech.amsterdam.recyclist.loader.ModelLoader;
import com.valtech.amsterdam.vris.model.ITimeSlot;
import com.valtech.amsterdam.vris.model.Reservation;
import com.valtech.amsterdam.vris.model.TimeSlotList;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by jasper.van.zijp on 18-7-2017.
 */

public class DummyTimeSlotLoader implements ModelLoader<ITimeSlot> {
    private ModelLoader<Reservation> reservationModelLoader;

    public DummyTimeSlotLoader(ModelLoader<Reservation> reservationModelLoader) {
        this.reservationModelLoader = reservationModelLoader;
    }

    @Override
    public ObservableList<ITimeSlot> getList() throws IOException {
        TimeSlotList timeslots = new TimeSlotList();
        timeslots.addAll(DummyContent.TIMESLOTS);
        timeslots.addAll(reservationModelLoader.getList());

        Collections.sort(timeslots, new Comparator<ITimeSlot>() {
            @Override
            public int compare(ITimeSlot o1, ITimeSlot o2) {
            return o1.getStartDate().compareTo(o2.getStartDate());
            }
        });

        return timeslots;
    }
}
