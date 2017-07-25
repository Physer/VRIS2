package com.valtech.amsterdam.vris.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.valtech.amsterdam.vris.business.loaders.ITimeSlotLoader;
import com.valtech.amsterdam.vris.model.ITimeSlot;
import com.valtech.amsterdam.vris.business.factories.TimeSlotDetailFragmentFactory;

import org.joda.time.DateTime;

/**
 * An activity representing a single Reservation detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link TimeSlotListActivity}.
 */
public class ReservationDetailActivity extends BaseActivity {

    private TimeSlotDetailFragmentFactory timeSlotDetailFragmentFactory;
    private ITimeSlotLoader timeSlotLoader;

//    public ReservationDetailActivity(TimeSlotDetailFragmentFactory timeSlotDetailFragmentFactory, ITimeSlotLoader timeSlotLoader) {
//        this.timeSlotDetailFragmentFactory = timeSlotDetailFragmentFactory;
//        this.timeSlotLoader = timeSlotLoader;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            ITimeSlot timeSlot;
            int itemId = getIntent().getIntExtra(ReservationDetailFragment.ARG_ITEM_ID, -1);
            if(itemId == -1){
                timeSlot = timeSlotLoader.getById(itemId);
                if(timeSlot == null) return;
            }
            else{
                timeSlot = timeSlotLoader.getByTime(DateTime.now().toLocalDateTime());
                if(timeSlot == null) return;
            }

            Fragment fragment = timeSlotDetailFragmentFactory.getDetailOrCreate(timeSlot);
            navigateToFragment(fragment, false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, TimeSlotListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
