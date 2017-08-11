package com.valtech.amsterdam.vris.business.loaders;

import com.valtech.amsterdam.recyclist.loader.ModelLoader;
import com.valtech.amsterdam.vris.model.Reservation;
import com.valtech.amsterdam.vris.model.ITimeSlot;
import com.valtech.amsterdam.vris.model.TimeSlotList;

import java.io.IOException;
import java.util.List;

/**
 * Created by jasper.van.zijp on 18-7-2017.
 */

public class TimeSlotLoader implements ModelLoader<ITimeSlot> {
    private ModelLoader<Reservation> reservationModelLoader;

    public TimeSlotLoader(ModelLoader<Reservation> reservationModelLoader) {
        this.reservationModelLoader = reservationModelLoader;
    }

    @Override
    public List<ITimeSlot> getList() throws IOException {
        List<Reservation> reservations = reservationModelLoader.getList();
        List<ITimeSlot> timeSlots = new TimeSlotList();

        for (Reservation reservation : reservations) {
            timeSlots.add(reservation);
        }

        return timeSlots;
    }
}
