package com.valtech.amsterdam.vris.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valtech.amsterdam.vris.VrisAppContext;
import com.valtech.amsterdam.vris.R;
import com.valtech.amsterdam.vris.databinding.TimeslotDetailReservationBinding;
import com.valtech.amsterdam.vris.model.Reservation;
import com.valtech.amsterdam.vris.model.Room;

import org.joda.time.DateTime;

/**
 * A fragment representing a single Reservation detail screen.
 * This fragment is either contained in a {@link TimeSlotListActivity}
 * on handsets.
 */
public class ReservationDetailFragment extends BaseTimeSlotFragment {

    private TimeslotDetailReservationBinding mTimeslotDetailReservationBinding;
    private BroadcastReceiver mBroadcastReceiver;
    public final String fDateFormat = "dd-MM-yyyy";
    public final String fTimeFormat = "HH:mm";

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mTimeslotDetailReservationBinding = DataBindingUtil.inflate( inflater, R.layout.timeslot_detail_reservation, container, false);

        if (mTimeSlot != null) {
            Reservation reservationItem = (Reservation)mTimeSlot;
            mTimeslotDetailReservationBinding.setFragment(this);
            mTimeslotDetailReservationBinding.setRoom(new Room(2, "Some room"));
            mTimeslotDetailReservationBinding.setDateTime(DateTime.now().toLocalDateTime());
            mTimeslotDetailReservationBinding.setReservation(reservationItem);
            mTimeslotDetailReservationBinding.reservationOrganizer.setOrganizer(reservationItem.getOrganizer());
            mTimeslotDetailReservationBinding.attendeeGrid.setAdapter(new AttendeeGridAdapter(this.getContext(), reservationItem));

            mBroadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context ctx, Intent intent) {
                    if (intent.getAction().compareTo(Intent.ACTION_TIME_TICK) == 0)
                        mTimeslotDetailReservationBinding.setDateTime(DateTime.now().toLocalDateTime());
                }
            };

            this.getContext().registerReceiver(mBroadcastReceiver, new IntentFilter(Intent.ACTION_TIME_TICK));
        }
        return mTimeslotDetailReservationBinding.getRoot();
    }

    public String formatReservationTimes(Reservation reservation){
        StringBuilder reservationTime = new StringBuilder();
        reservationTime.append(reservation.getStartDate().toString("HH:mm"));
        reservationTime.append(" - ");
        reservationTime.append(reservation.getEndDate().toString("HH:mm"));
        return reservationTime.toString();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mBroadcastReceiver != null) this.getContext().unregisterReceiver(mBroadcastReceiver);
    }
}
