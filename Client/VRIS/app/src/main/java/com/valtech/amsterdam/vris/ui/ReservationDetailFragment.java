package com.valtech.amsterdam.vris.ui;

import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.valtech.amsterdam.vris.R;
import com.valtech.amsterdam.vris.dummy.DummyContent;
import com.valtech.amsterdam.vris.model.Reservation;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * A fragment representing a single Reservation detail screen.
 * This fragment is either contained in a {@link TimeSlotListActivity}
 * on handsets.
 */
public class ReservationDetailFragment extends BaseFragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Reservation reservationItem;
    private View rootView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ReservationDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            reservationItem = DummyContent.RESERVATIONS_MAP.get(getArguments().getInt(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.reservation_detail, container, false);

        SetDefaultTextViews();
        if (reservationItem != null) SetReservationTextViews();

        return rootView;
    }

    private void SetReservationTextViews() {
        TextView reservationTitleElement = (TextView) rootView.findViewById(R.id.reservation_title);
        TextView reservationTimeElement = (TextView) rootView.findViewById(R.id.reservation_time);
        TextView bookerElement = (TextView) rootView.findViewById(R.id.booker);

        StringBuilder reservationTime = new StringBuilder();
        reservationTime.append(reservationItem.getStartDate().toString("HH:mm"));
        reservationTime.append(" - ");
        reservationTime.append(reservationItem.getEndDate().toString("HH:mm"));

        reservationTitleElement.setText(reservationItem.getTitle());
        reservationTimeElement.setText(reservationTime.toString());
        bookerElement.setText(reservationItem.getBooker().getName());
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
