package com.valtech.amsterdam.vris.ui;

import android.view.View;
import android.widget.TextView;

import com.valtech.amsterdam.recyclist.RecyclistViewBinder;
import com.valtech.amsterdam.vris.R;
import com.valtech.amsterdam.vris.model.ITimeSlot;
import com.valtech.amsterdam.vris.model.Reservation;

/**
 * Created by jasper.van.zijp on 14-7-2017.
 */

public class TimeSlotViewBinder implements RecyclistViewBinder<ITimeSlot> {
    @Override
    public void bindView(View view, final ITimeSlot timeSlot, final OnClickListener clickListener) {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(timeSlot);
            }
        };

        if(timeSlot instanceof Reservation) {
            ((TextView) view.findViewById(R.id.booker)).setText(((Reservation)timeSlot).getBooker().getName());
        }
        else{
            view.findViewById(R.id.button).setOnClickListener(onClickListener);
        }

        ((TextView) view.findViewById(R.id.from)).setText(timeSlot.getStartDate().toString("HH:mm"));
        ((TextView) view.findViewById(R.id.to)).setText(timeSlot.getEndDate().toString("HH:mm"));

        view.setOnClickListener(onClickListener);
    }
}
