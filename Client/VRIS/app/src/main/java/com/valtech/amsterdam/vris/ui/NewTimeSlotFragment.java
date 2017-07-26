package com.valtech.amsterdam.vris.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valtech.amsterdam.vris.CustomApplication;
import com.valtech.amsterdam.vris.R;
import com.valtech.amsterdam.vris.model.ITimeSlot;

/**
 * A fragment representing a single Reservation detail screen.
 * This fragment is either contained in a {@link TimeSlotListActivity}
 * on handsets.
 */
public class NewTimeSlotFragment extends BaseTimeSlotFragment {

    /**
     * The dummy content this fragment is presenting.
     */
    @Nullable
    private ITimeSlot timeSlot;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NewTimeSlotFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.w("NewTimeSlotFragment","onCreate");
        ((CustomApplication)getActivity().getApplicationContext()).getApplicationComponent().inject(this); //This makes the members injected
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void timeSlotLoaded(ITimeSlot timeSlot) {
        Log.w("NewTimeSlotFragment","timeSlotLoaded ("+timeSlot.getId()+")");
        this.timeSlot = timeSlot;
    }
    @Override
    protected void selectTimeSlot() {
        Log.w("NewTimeSlotFragment","selectTimeSlot ("+timeSlot.getId()+")");
        timeSlotLoader.select(timeSlot);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.timeslot_new, container, false);

        return rootView;
    }
}
