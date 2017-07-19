package com.valtech.amsterdam.vris.ui;

import android.view.View;
import android.widget.TextView;

import com.valtech.amsterdam.recyclist.RecyclistViewBinder;
import com.valtech.amsterdam.vris.R;
import com.valtech.amsterdam.vris.model.ITimeSlot;
import com.valtech.amsterdam.vris.model.Reservation;

import java.text.SimpleDateFormat;

/**
 * Created by jasper.van.zijp on 14-7-2017.
 */

public class TimeSlotViewBinder implements RecyclistViewBinder<ITimeSlot> {
    @Override
    public void bindView(View view, final ITimeSlot timeSlot, final OnClickListener clickListener) {
        if(timeSlot instanceof Reservation) {
            ((TextView) view.findViewById(R.id.booker)).setText(((Reservation)timeSlot).getBooker().getName());
        }

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

        ((TextView) view.findViewById(R.id.from)).setText(formatter.format(timeSlot.getStart()));
        ((TextView) view.findViewById(R.id.to)).setText(formatter.format(timeSlot.getEnd()));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(timeSlot);
            }
        });
    }
}
