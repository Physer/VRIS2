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
import com.valtech.amsterdam.vris.databinding.TimeslotDetailNewBinding;
import com.valtech.amsterdam.vris.model.Person;
import com.valtech.amsterdam.vris.model.Reservation;
import com.valtech.amsterdam.vris.model.Room;

import org.joda.time.DateTime;

/**
 * A fragment representing a single Reservation detail screen.
 * This fragment is either contained in a {@link TimeSlotListActivity}
 * on handsets.
 */
public class NewTimeSlotFragment extends BaseTimeSlotFragment {

    private TimeslotDetailNewBinding mNewTimeSlotBinding;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NewTimeSlotFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.w("NewTimeSlotFragment","onCreate");
        ((VrisAppContext)getActivity().getApplicationContext()).getApplicationComponent().inject(this); //This makes the members injected
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mNewTimeSlotBinding = DataBindingUtil.inflate( inflater, R.layout.timeslot_detail_new, container, false);

        if (mTimeSlot != null) {
            // todo a lot
            Reservation reservationItem = new Reservation(-1, null, mTimeSlot.getStart(), mTimeSlot.getEnd(), new Person(-1, "Vris"), null);
            // todo resolve room
            mNewTimeSlotBinding.setRoom(new Room(2, "AMS 0X"));
            mNewTimeSlotBinding.setNewReservation(reservationItem);
        }
        return mNewTimeSlotBinding.getRoot();
    }
}
