package com.valtech.amsterdam.vris.viewSelectors;

import com.valtech.amsterdam.recyclist.ViewSelector;
import com.valtech.amsterdam.vris.R;
import com.valtech.amsterdam.vris.model.ITimeSlot;
import com.valtech.amsterdam.vris.model.Reservation;

/**
 * Created by jasper.van.zijp on 18-7-2017.
 */

public class TimeSlotItemViewSelector implements ViewSelector<ITimeSlot> {
    @Override
    public int getViewResourceId(ITimeSlot object) {

        if (object instanceof Reservation)
            return R.layout.timeslot_item_reservation;

        return R.layout.timeslot_item_open;
    }
}
