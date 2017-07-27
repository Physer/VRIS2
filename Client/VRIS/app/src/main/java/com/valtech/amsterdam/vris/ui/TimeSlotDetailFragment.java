package com.valtech.amsterdam.vris.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valtech.amsterdam.vris.VrisAppContext;
import com.valtech.amsterdam.vris.R;
import com.valtech.amsterdam.vris.business.services.navigation.INavigationService;

import javax.inject.Inject;

/**
 * A fragment representing a single Reservation detail screen.
 * This fragment is either contained in a {@link TimeSlotListActivity}
 * on handsets.
 */
public class TimeSlotDetailFragment extends BaseTimeSlotFragment {

    @Inject
    INavigationService navigationService;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TimeSlotDetailFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("TimeSlotDetailFragment","onCreate");
        ((VrisAppContext)getActivity().getApplicationContext()).getApplicationComponent().inject(this); //This makes the members injected
        super.onCreate(savedInstanceState);
        navigationService.setCurrentActivity((BaseActivity) this.getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.timeslot_detail_open, container, false);

        rootView.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                navigationService.navigateToTimeSlot(mTimeSlot);
                }
        });

        return rootView;
    }
}
