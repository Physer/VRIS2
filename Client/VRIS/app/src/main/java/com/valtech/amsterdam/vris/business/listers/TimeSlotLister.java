package com.valtech.amsterdam.vris.business.listers;

import com.valtech.amsterdam.recyclist.loader.ModelLoader;
import com.valtech.amsterdam.vris.model.Reservation;
import com.valtech.amsterdam.vris.model.ITimeSlot;

import org.joda.time.DateTime;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

/**
 * Created by jasper.van.zijp on 18-7-2017.
 */

public class TimeSlotLister implements ModelLoader<ITimeSlot> {
    private ModelLoader<Reservation> reservationModelLoader;

    public TimeSlotLister(ModelLoader<Reservation> reservationModelLoader) {
        this.reservationModelLoader = reservationModelLoader;
    }

    @Override
    public List<ITimeSlot> getList() throws IOException {
        List<Reservation> reservations = reservationModelLoader.getList();

        List<ITimeSlot> timeSlots = new ArrayList<>();

        for (Reservation reservation :
                reservations) {
            timeSlots.add(reservation);
        }

        return timeSlots;
    }
}
