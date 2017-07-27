package com.valtech.amsterdam.vris.ui;

import android.content.res.ColorStateList;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
    public void bindView(View view, final ITimeSlot timeSlot, final OnClickListener clickListener, final int position) {

        RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.timeslot_layout);
        RelativeLayout shadow = (RelativeLayout) layout.findViewById(R.id.timeslot_shadow);
        TextView titleElement = (TextView) view.findViewById(R.id.reservation_title);
        TextView startTimeElement = (TextView) view.findViewById(R.id.from);
        TextView endTimeElement = (TextView) view.findViewById(R.id.to);
        ViewGroup.LayoutParams layoutParams = layout.getLayoutParams();

        shadow.setVisibility(timeSlot.getSelected() ? LinearLayout.INVISIBLE : LinearLayout.VISIBLE);
        if(timeSlot instanceof Reservation) {
            titleElement.setText(((Reservation)timeSlot).getmTitle());

            if (timeSlot.getSelected()) view.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(),R.color.colorSlotSelected)));
            else if (position % 2 == 1) view.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(),R.color.colorSlotOdd)));
            else view.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(),R.color.colorSlotEven)));
        }
        else{
            if (timeSlot.getSelected()) view.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(),R.color.colorSlotOpenSelected)));
            else view.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(),R.color.colorSlotOpen)));
        }

        SetDynamicHeigthAccordingToSlotTime(view, timeSlot, layoutParams);
        layout.setLayoutParams(layoutParams);
        startTimeElement.setText(timeSlot.getStartDate().toString("HH:mm"));
        endTimeElement.setText(timeSlot.getEndDate().toString("HH:mm"));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(timeSlot, position);
            }
        });
    }

    private void SetDynamicHeigthAccordingToSlotTime(View view, ITimeSlot timeSlot, ViewGroup.LayoutParams params) {
        int partMinutes = view.getContext().getResources().getInteger(R.integer.time_slot_part_minutes);
        int partHeight = view.getContext().getResources().getInteger(R.integer.time_slot_part_heigth);
        int startHeight = view.getContext().getResources().getInteger(R.integer.time_slot_start_height);

        int partCount = FitsAmount(timeSlot.getDurationInMinutes(), partMinutes);
        int slotHeight = startHeight + partCount * partHeight;
        params.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, slotHeight, view.getContext().getResources().getDisplayMetrics());
    }

    public static int FitsAmount(int number, int fittingNumber) {
        if (fittingNumber > number) throw new IllegalArgumentException();

        return Math.round(number / fittingNumber);
    }
}
