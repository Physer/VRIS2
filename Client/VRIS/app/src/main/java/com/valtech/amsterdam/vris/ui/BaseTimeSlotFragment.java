package com.valtech.amsterdam.vris.ui;

import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.valtech.amsterdam.vris.business.services.navigation.INavigationService;
import com.valtech.amsterdam.vris.business.services.navigation.NavigationService;
import com.valtech.amsterdam.vris.model.ITimeSlot;

import javax.inject.Inject;

/**
 * Created by marvin.brouwer on 20-7-2017.
 */

public abstract class BaseTimeSlotFragment extends BaseFragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    public static final String ARG_HIDE_KEYBOARD = "hide_keyboard";

    @Nullable
    protected BroadcastReceiver mBroadcastReceiver;
    @Nullable
    protected ITimeSlot mTimeSlot;

    // todo remove find out another way to centralize getting a single timeslot
    @Inject
    INavigationService navigationService;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadTimeSlotInView();
    }

    private void loadTimeSlotInView() {
        if (getArguments() != null && getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            // todo find out another way to centralize getting a single timeslot
            mTimeSlot = ((NavigationService)navigationService).getTimeSlotUpdater().getById(getArguments().getInt(ARG_ITEM_ID));
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        try {
            if (mBroadcastReceiver != null)
                this.getContext().unregisterReceiver(mBroadcastReceiver);
        }
        catch (IllegalArgumentException e){
            return;
        }
    }
}