package com.valtech.amsterdam.vris.business.loaders;

import com.valtech.amsterdam.recyclist.modifiers.Updater;
import com.valtech.amsterdam.vris.model.ITimeSlot;

import org.joda.time.LocalDateTime;

import java.util.List;

/**
 * Created by marvin.brouwer on 20-7-2017.
 */

public interface ITimeSlotLoader {
    List<ITimeSlot> getList();
    ITimeSlot getByTime(LocalDateTime date) throws IndexOutOfBoundsException;
    ITimeSlot getById(int id) throws IndexOutOfBoundsException;

    void select(ITimeSlot timeSlot);
    void setUpdater(Updater<ITimeSlot> updater);
}
