package com.valtech.amsterdam.vris.ui;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.valtech.amsterdam.vris.R;
import com.valtech.amsterdam.vris.databinding.PersonAttendeeBinding;
import com.valtech.amsterdam.vris.model.Person;
import com.valtech.amsterdam.vris.model.Reservation;

import java.util.List;

/**
 * Created by marvin.brouwer on 27-7-2017.
 */

public final class AttendeeGridAdapter extends BaseAdapter {
    private final Reservation fReservation;
    private final Context fContext;
    @Nullable
    private List<Person> mAttendees;

    public AttendeeGridAdapter(Context context, Reservation reservation) {
        fContext = context;
        fReservation = reservation;
        mAttendees = fReservation.getAttendees();
    }

    public int getCount() {
        if(mAttendees == null) return 0;
        return mAttendees.size();
    }

    public Object getItem(int position) {
        return mAttendees.get(position);
    }

    public long getItemId(int position) {
        return mAttendees.get(position).getId();
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        Person attendee = mAttendees.get(position);
        if(attendee == null) return null;

        LayoutInflater inflater = (LayoutInflater) fContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        PersonAttendeeBinding attendeeBinding = DataBindingUtil.inflate( inflater, R.layout.person_attendee, parent, false);
        attendeeBinding.setAttendee(attendee);

        View gridItem = attendeeBinding.getRoot();

        int cellHeightDip = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, gridItem.getContext().getResources().getDisplayMetrics());
        gridItem.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, cellHeightDip));
        // todo lazy load image here or in xml?

        return gridItem;
    }
}
