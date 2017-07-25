package com.valtech.amsterdam.vris.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valtech.amsterdam.vris.R;
import com.valtech.amsterdam.vris.model.TimeSlot;

/**
 * A fragment representing a single Reservation detail screen.
 * This fragment is either contained in a {@link TimeSlotListActivity}
 * in two-pane mode (on tablets) or a {@link ReservationDetailActivity}
 * on handsets.
 */
public class NewTimeSlotFragment extends BaseFragment {

    /**
     * The dummy content this fragment is presenting.
     */
    private TimeSlot mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NewTimeSlotFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.timeslot_new, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            //
        }

        return rootView;
    }
}
