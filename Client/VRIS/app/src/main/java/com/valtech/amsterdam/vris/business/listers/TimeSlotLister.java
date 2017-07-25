package com.valtech.amsterdam.vris.business.listers;

import com.valtech.amsterdam.recyclist.loader.ModelLoader;
import com.valtech.amsterdam.vris.model.Reservation;
import com.valtech.amsterdam.vris.model.ITimeSlot;
import com.valtech.amsterdam.vris.model.TimeSlotList;

import java.io.IOException;
import java.util.ArrayList;

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
        ArrayList<Reservation> reservations = reservationModelLoader.getList();

        TimeSlotList timeSlots = new TimeSlotList();

        for (Reservation reservation : reservations) {
            timeSlots.add(reservation);
        }

        return timeSlots;
    }
}
