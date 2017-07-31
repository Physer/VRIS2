package com.valtech.amsterdam.vris.ui;

import android.content.Context;
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
import com.valtech.amsterdam.vris.databinding.TimeslotDetailNewBinding;
import com.valtech.amsterdam.vris.model.Person;
import com.valtech.amsterdam.vris.model.Reservation;
import com.valtech.amsterdam.vris.model.Room;

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
        FocusAndShowKeyboard();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mNewTimeSlotBinding = DataBindingUtil.inflate( inflater, R.layout.timeslot_detail_new, container, false);

        if (mTimeSlot != null) {
            // todo a lot
            mReservationItem = new Reservation(-1, null, mTimeSlot.getStart(), mTimeSlot.getEnd(), new Person(-1, "Vris"), null);
            // todo resolve room
            mNewTimeSlotBinding.setContext(this);
            mNewTimeSlotBinding.setRoom(new Room(2, "AMS 0X"));
            mNewTimeSlotBinding.setNewReservation(mReservationItem);
        }
        return mNewTimeSlotBinding.getRoot();
    }

    public void onSubmit(int minuteAmount){
        String title = mReservationItem.getTitle();
        if(title == null || title.trim().isEmpty()) {
            FocusAndShowKeyboard();
            mTextField.startAnimation(fShakeAnimation);
            return;
        }
        // todo add to list
        Log.i("title", mReservationItem.getTitle());
        Log.i("submit",String.valueOf( minuteAmount));

        /*todo navigationService.navigateToTimeSlot(mReservationItem);*/ navigationService.navigateToHomeSlot();
    }

    private void FocusAndShowKeyboard() {
        mTextField = (EditText)mNewTimeSlotBinding.getRoot().findViewById(R.id.editText);
        mTextField.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(mTextField, 0);
    }
}
