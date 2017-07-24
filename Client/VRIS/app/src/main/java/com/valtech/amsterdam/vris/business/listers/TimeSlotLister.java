package com.valtech.amsterdam.vris.business.listers;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.valtech.amsterdam.recyclist.loader.ModelLoader;
import com.valtech.amsterdam.vris.model.Reservation;
import com.valtech.amsterdam.vris.model.ITimeSlot;
import com.valtech.amsterdam.vris.model.TimeSlotList;

import java.io.IOException;

/**
 * Created by jasper.van.zijp on 18-7-2017.
 */

public class TimeSlotLister implements ModelLoader<ITimeSlot> {
    private ModelLoader<Reservation> reservationModelLoader;

    public TimeSlotLister(ModelLoader<Reservation> reservationModelLoader) {
        this.reservationModelLoader = reservationModelLoader;
    }

    @Override
    public TimeSlotList getList() throws IOException {
        ObservableList<Reservation> reservations = reservationModelLoader.getList();

        TimeSlotList timeSlots = new TimeSlotList();

        for (Reservation reservation : reservations) {
            timeSlots.add(reservation);
        }

        return timeSlots;
    }
}
