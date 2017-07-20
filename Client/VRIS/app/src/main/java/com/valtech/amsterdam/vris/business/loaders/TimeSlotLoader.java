package com.valtech.amsterdam.vris.business.loaders;

import com.valtech.amsterdam.recyclist.loader.ModelLoader;
import com.valtech.amsterdam.vris.model.ITimeSlot;
import com.valtech.amsterdam.vris.model.Reservation;

import org.joda.time.DateTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marvin.brouwer on 20-7-2017.
 */

public final class TimeSlotLoader implements ITimeSlotLoader {
    private ModelLoader<ITimeSlot> reservationModelLoader;

    public TimeSlotLoader(ModelLoader<ITimeSlot> reservationModelLoader) {
        this.reservationModelLoader = reservationModelLoader;
    }

    public List<ITimeSlot> getList() {
        List<ITimeSlot> timeSlots;
        try {
            timeSlots = reservationModelLoader.getList();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return timeSlots;
    }

    public ITimeSlot getByTime(DateTime date) throws IndexOutOfBoundsException {
        List<ITimeSlot> timeSlots = getList();
        if(timeSlots ==  null) return null;

        for (ITimeSlot timeSlot: timeSlots) {
            DateTime startDate = timeSlot.getStartDate();
            DateTime endDate = timeSlot.getEndDate();

            if(date.isBefore(startDate)) continue;
            if(date.isAfter(endDate)) continue;

            return timeSlot;
        }

        throw new IndexOutOfBoundsException();
    }

    public ITimeSlot getById(int id) throws IndexOutOfBoundsException {

        if(id == -1) return null;
        List<ITimeSlot> timeSlots = getList();
        if(timeSlots ==  null) return null;

        for (ITimeSlot timeSlot: timeSlots) {
            if(id != timeSlot.getId()) continue;
            return timeSlot;
        }

        throw new IndexOutOfBoundsException();
    }
}
