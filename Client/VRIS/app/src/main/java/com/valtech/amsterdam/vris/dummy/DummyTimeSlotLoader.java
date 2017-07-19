package com.valtech.amsterdam.vris.dummy;

import com.valtech.amsterdam.recyclist.loader.ModelLoader;
import com.valtech.amsterdam.vris.model.Reservation;
import com.valtech.amsterdam.vris.model.TimeSlot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by jasper.van.zijp on 18-7-2017.
 */

public class DummyTimeSlotLoader implements ModelLoader<TimeSlot> {
    private ModelLoader<Reservation> reservationModelLoader;

    public DummyTimeSlotLoader(ModelLoader<Reservation> reservationModelLoader) {
        this.reservationModelLoader = reservationModelLoader;
    }

    @Override
    public List<TimeSlot> getList() throws IOException {
        List<TimeSlot> timeslots = new ArrayList<>();
        timeslots.addAll(DummyContent.TIMESLOTS);
        timeslots.addAll(reservationModelLoader.getList());

        Collections.sort(timeslots, new Comparator<TimeSlot>() {
            @Override
            public int compare(TimeSlot o1, TimeSlot o2) {
                return o1.getStart().compareTo(o2.getStart());
            }
        });

        return timeslots;
    }
}
