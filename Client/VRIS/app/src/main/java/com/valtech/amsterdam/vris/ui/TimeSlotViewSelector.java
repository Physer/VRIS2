package com.valtech.amsterdam.vris.ui;

import com.valtech.amsterdam.recyclist.ViewSelector;
import com.valtech.amsterdam.vris.R;
import com.valtech.amsterdam.vris.model.Reservation;
import com.valtech.amsterdam.vris.model.TimeSlot;

/**
 * Created by jasper.van.zijp on 18-7-2017.
 */

public class TimeSlotViewSelector implements ViewSelector<TimeSlot> {
    @Override
    public int getViewResourceId(TimeSlot object) {
        if (object instanceof Reservation)
            return R.layout.timeslot_list_reservation_item;
        else
            return R.layout.timeslot_list_timeslot_item;

    }
}
