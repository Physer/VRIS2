package com.valtech.amsterdam.vris.business.loaders;

import android.support.annotation.Nullable;

import com.valtech.amsterdam.recyclist.loader.ModelLoader;
import com.valtech.amsterdam.recyclist.modifiers.Updater;
import com.valtech.amsterdam.vris.model.ITimeSlot;
import com.valtech.amsterdam.vris.model.TimeSlotList;

import org.joda.time.LocalDateTime;

import java.io.IOException;

/**
 * Created by marvin.brouwer on 20-7-2017.
 */

public final class TimeSlotLoader implements ITimeSlotLoader {
    @Nullable
    private Updater<ITimeSlot> updater;
    private ModelLoader<ITimeSlot> reservationModelLoader;

    public TimeSlotLoader(ModelLoader<ITimeSlot> reservationModelLoader) {
        this.reservationModelLoader = reservationModelLoader;
    }

    public void setUpdater(Updater<ITimeSlot> updater){
        this.updater = updater;
    }

    public TimeSlotList getList() {
        TimeSlotList timeSlots;
        try {
            timeSlots = (TimeSlotList) reservationModelLoader.getList();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return timeSlots;
    }

    public ITimeSlot getByTime(LocalDateTime date) throws IndexOutOfBoundsException {
        TimeSlotList timeSlots = getList();
        if(timeSlots ==  null) return null;

        for (ITimeSlot timeSlot: timeSlots) {
            LocalDateTime startDate = timeSlot.getStartDate();
            LocalDateTime endDate = timeSlot.getEndDate();

            if(date.isBefore(startDate)) continue;
            if(date.isAfter(endDate)) continue;

            return timeSlot;
        }

        throw new IndexOutOfBoundsException();
    }

    public ITimeSlot getById(int id) throws IndexOutOfBoundsException {

        if(id == -1) return null;
        TimeSlotList timeSlots = getList();
        if(timeSlots ==  null) return null;

        for (ITimeSlot timeSlot: timeSlots) {
            if(id != timeSlot.getId()) continue;
            return timeSlot;
        }

        throw new IndexOutOfBoundsException();
    }

    public void select(ITimeSlot timeSlot){
        TimeSlotList timeSlots = getList();

        for (ITimeSlot timeSlotItem: timeSlots) {
            if(timeSlotItem.getId() == timeSlot.getId()) timeSlotItem.setSelected(true);
            else if(timeSlotItem.getSelected() == true) {
                timeSlotItem.setSelected(false);
            } else continue;

            if(this.updater != null) this.updater.update(timeSlotItem);
        }
        if(this.updater != null) this.updater.notifyItemUpdated();
    }
}
