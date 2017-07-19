package com.valtech.amsterdam.vris.viewSelectors;

import com.valtech.amsterdam.recyclist.ViewSelector;
import com.valtech.amsterdam.vris.R;
import com.valtech.amsterdam.vris.model.ITimeSlot;
import com.valtech.amsterdam.vris.model.Reservation;
import com.valtech.amsterdam.vris.model.TimeSlot;

/**
 * Created by jasper.van.zijp on 18-7-2017.
 */

public class TimeSlotItemViewSelector implements ViewSelector<ITimeSlot> {
    @Override
    public int getViewResourceId(ITimeSlot object) {

        if (object instanceof Reservation)
            return R.layout.timeslot_list_reservation_item;

        return R.layout.timeslot_list_timeslot_item;
    }
}
