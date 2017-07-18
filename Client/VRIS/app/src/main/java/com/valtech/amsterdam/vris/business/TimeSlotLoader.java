package com.valtech.amsterdam.vris.business;

import com.valtech.amsterdam.recyclist.loader.ModelLoader;
import com.valtech.amsterdam.vris.model.Person;
import com.valtech.amsterdam.vris.model.Reservation;
import com.valtech.amsterdam.vris.model.TimeSlot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by jasper.van.zijp on 18-7-2017.
 */

public class TimeSlotLoader implements ModelLoader<TimeSlot> {
    private ModelLoader<Reservation> reservationModelLoader;

    public TimeSlotLoader(ModelLoader<Reservation> reservationModelLoader) {
        this.reservationModelLoader = reservationModelLoader;
    }

    @Override
    public List<TimeSlot> getList() throws IOException {
        List<Reservation> reservations = reservationModelLoader.getList();
        List<TimeSlot> timeSlots = new ArrayList<>();

        for (Reservation reservation :
                reservations) {
            timeSlots.add(reservation);
        }

        return timeSlots;
    }
}
