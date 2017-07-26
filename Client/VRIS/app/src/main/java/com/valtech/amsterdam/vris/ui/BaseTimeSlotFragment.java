package com.valtech.amsterdam.vris.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.valtech.amsterdam.vris.business.loaders.ITimeSlotLoader;
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

    @Inject
    ITimeSlotLoader timeSlotLoader;
    private int stackId;
    private boolean initiated = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        stackId = fragmentManager.getBackStackEntryCount();
        if(initiated) return;

        loadTimeSlotInView();
        selectTimeSlot();
        initiated = true;

        fragmentManager.addOnBackStackChangedListener(
            new FragmentManager.OnBackStackChangedListener() {
                public void onBackStackChanged() {
                    int navigatingStackId = fragmentManager.getBackStackEntryCount();

                    if(stackId == navigatingStackId) selectTimeSlot();
                }
            });
    }

    private void loadTimeSlotInView() {
        if (getArguments() != null && getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            ITimeSlot timeSlot = timeSlotLoader.getById(getArguments().getInt(ARG_ITEM_ID));
            if(timeSlot != null ) {
                timeSlotLoaded(timeSlot);
            }
        }
    }

    protected abstract void timeSlotLoaded(ITimeSlot timeSlot);
    protected abstract void selectTimeSlot();

}