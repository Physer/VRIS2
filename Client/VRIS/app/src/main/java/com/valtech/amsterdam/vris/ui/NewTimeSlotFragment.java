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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.valtech.amsterdam.vris.VrisAppContext;
import com.valtech.amsterdam.vris.R;
import com.valtech.amsterdam.vris.business.services.navigation.NavigationService;
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
    private Reservation mReservationItem;
    private EditText mTextField;
    private Animation fShakeAnimation;

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
        fShakeAnimation = AnimationUtils.loadAnimation(getContext() ,R.anim.shake);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        mTextField = (EditText)mNewTimeSlotBinding.getRoot().findViewById(R.id.editText);
        focus();
        if (getArguments() != null && getArguments().containsKey(ARG_HIDE_KEYBOARD)) {
            if(getArguments().getInt(ARG_HIDE_KEYBOARD) == 0) return;
        }
        focusAndShowKeyboard();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mNewTimeSlotBinding = DataBindingUtil.inflate( inflater, R.layout.timeslot_detail_new, container, false);

        if (mTimeSlot != null) {
            // todo a lot
            mReservationItem = new Reservation(-1, null, mTimeSlot.getStart(), mTimeSlot.getEnd(), new Person(-1, "Vris"), null);
            // todo resolve room
            mNewTimeSlotBinding.setContext(this);
            mNewTimeSlotBinding.setDateTime(DateTime.now().toLocalDateTime());
            mNewTimeSlotBinding.setRoom(new Room(2, "AMS 0X"));
            mNewTimeSlotBinding.setNewReservation(mReservationItem);
            mNewTimeSlotBinding.setNewReservation(mReservationItem);

        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context ctx, Intent intent) {
                if (intent.getAction().compareTo(Intent.ACTION_TIME_TICK) == 0)
                    mNewTimeSlotBinding.setDateTime(DateTime.now().toLocalDateTime());
            }
        };

        this.getContext().registerReceiver(mBroadcastReceiver, new IntentFilter(Intent.ACTION_TIME_TICK));

        }
        return mNewTimeSlotBinding.getRoot();
    }

    public void onSubmit(int minuteAmount){
        String title = mReservationItem.getTitle();
        if(title == null || title.trim().isEmpty()) {
            focusAndShowKeyboard();
            mTextField.startAnimation(fShakeAnimation);
            return;
        }
        // todo add to list
        Log.i("title", mReservationItem.getTitle());
        Log.i("submit",String.valueOf( minuteAmount));

        /*todo navigationService.navigateToTimeSlot(mReservationItem);*/ navigationService.navigateToHomeSlot();
        // todo scroll to self instead of top
        ((TimeSlotListActivity)getActivity()).
                getRecyclerView().getLayoutManager().scrollToPosition(0);
    }

    private void focusAndShowKeyboard() {
        focus();
        InputMethodManager inputMethodManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(mTextField, 0);
    }

    private void focus() {
        mTextField.requestFocus();
    }
}
