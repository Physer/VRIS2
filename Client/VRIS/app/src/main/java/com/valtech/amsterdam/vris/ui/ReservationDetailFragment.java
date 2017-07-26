package com.valtech.amsterdam.vris.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.valtech.amsterdam.vris.VrisAppContext;
import com.valtech.amsterdam.vris.R;
import com.valtech.amsterdam.vris.model.ITimeSlot;
import com.valtech.amsterdam.vris.model.Reservation;

import org.joda.time.DateTime;

/**
 * A fragment representing a single Reservation detail screen.
 * This fragment is either contained in a {@link TimeSlotListActivity}
 * on handsets.
 */
public class ReservationDetailFragment extends BaseTimeSlotFragment {

    /**
     * The dummy content this fragment is presenting.
     */
    private Reservation reservationItem;
    private View rootView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ReservationDetailFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("ReservationDetail...","onCreate");
        ((VrisAppContext)getActivity().getApplicationContext()).getApplicationComponent().inject(this); //This makes the members injected
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void timeSlotLoaded(ITimeSlot timeSlot) {
        Log.w("ReservationDetail...","timeSlotLoaded ("+timeSlot.getId()+")");
        // This has to be
        this.reservationItem = (Reservation) timeSlot;
    }

    @Override
    protected void selectTimeSlot() {
        Log.w("ReservationDetail...","selectTimeSlot ("+reservationItem.getId()+")");
        timeSlotLoader.select(reservationItem);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.timeslot_detail_reservation, container, false);

        SetDefaultTextViews();
        if (reservationItem != null) {
            SetReservationTextViews();
        }

        return rootView;
    }

    private void SetReservationTextViews() {
        TextView reservationTitleElement = (TextView) rootView.findViewById(R.id.reservation_title);
        TextView reservationTimeElement = (TextView) rootView.findViewById(R.id.reservation_time);
        TextView organizerNameElement = (TextView) rootView.findViewById(R.id.organizer_name);
        TextView organizerEmailElement = (TextView) rootView.findViewById(R.id.organizer_email);
        ImageView organizerIconElement = (ImageView) rootView.findViewById(R.id.organizer_image);

        StringBuilder reservationTime = new StringBuilder();
        reservationTime.append(reservationItem.getStartDate().toString("HH:mm"));
        reservationTime.append(" - ");
        reservationTime.append(reservationItem.getEndDate().toString("HH:mm"));

        reservationTitleElement.setText(reservationItem.getmTitle());
        reservationTimeElement.setText(reservationTime.toString());
        organizerNameElement.setText(reservationItem.getOrganizer().getName());
        organizerEmailElement.setText(reservationItem.getOrganizer().getEmail());
    }

    private void SetDefaultTextViews() {
        TextView currentRoomElement = (TextView) rootView.findViewById(R.id.current_room);
        TextView currentDateElement = (TextView) rootView.findViewById(R.id.current_date);
        TextView currentTimeElement = (TextView) rootView.findViewById(R.id.current_time);

        currentDateElement.setText(DateTime.now().toLocalDateTime().toString("dd-MM-yyyy"));
        currentTimeElement.setText(DateTime.now().toLocalDateTime().toString("HH:mm"));
    }

    BroadcastReceiver _broadcastReceiver;
    private TextView _tvTime;

    // todo create something like this in the listActivity and re evaluate if homestate
    @Override
    public void onStart() {
        super.onStart();
        _broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context ctx, Intent intent) {
                if (intent.getAction().compareTo(Intent.ACTION_TIME_TICK) == 0)
                    SetDefaultTextViews();
            }
        };

        this.getContext().registerReceiver(_broadcastReceiver, new IntentFilter(Intent.ACTION_TIME_TICK));
    }

    @Override
    public void onStop() {
        super.onStop();
        if (_broadcastReceiver != null)
            this.getContext().unregisterReceiver(_broadcastReceiver);
    }
}
