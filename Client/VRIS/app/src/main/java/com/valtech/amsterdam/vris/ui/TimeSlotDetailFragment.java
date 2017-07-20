package com.valtech.amsterdam.vris.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.valtech.amsterdam.vris.R;
import com.valtech.amsterdam.vris.dummy.DummyContent;
import com.valtech.amsterdam.vris.model.Reservation;
import com.valtech.amsterdam.vris.model.TimeSlot;

import java.text.SimpleDateFormat;

/**
 * A fragment representing a single Reservation detail screen.
 * This fragment is either contained in a {@link TimeSlotListActivity}
 * in two-pane mode (on tablets) or a {@link ReservationDetailActivity}
 * on handsets.
 */
public class TimeSlotDetailFragment extends BaseFragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private TimeSlot mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TimeSlotDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.RESERVATIONS_MAP.get(getArguments().getInt(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle("Time slot");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.timeslot_detail, container, false);

        rootView.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
            Fragment fragment = new NewTimeSlotFragment();
            navigateToFragment(fragment, true);
                }
        });

        return rootView;
    }
}
