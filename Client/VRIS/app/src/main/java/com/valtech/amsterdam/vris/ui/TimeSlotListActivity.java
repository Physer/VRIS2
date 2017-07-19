package com.valtech.amsterdam.vris.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;


import com.valtech.amsterdam.recyclist.Recyclist;
import com.valtech.amsterdam.recyclist.Recyclistener;
import com.valtech.amsterdam.vris.DaggerInjectionComponent;
import com.valtech.amsterdam.vris.InjectionComponent;
import com.valtech.amsterdam.vris.R;
import com.valtech.amsterdam.vris.model.ITimeSlot;
import com.valtech.amsterdam.vris.model.TimeSlot;
import com.valtech.amsterdam.vris.viewSelectors.TimeSlotDetailFragmentFactory;

import javax.inject.Inject;

/**
 * An activity representing a list of Reservations. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ReservationDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class TimeSlotListActivity extends AppCompatActivity implements Recyclistener, OnClickListener {
    private final static String fLogTag = "TimeSlotListActivity";

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Inject
    Recyclist<ITimeSlot> recyclist;
    @Inject
    TimeSlotDetailFragmentFactory timeSlotDetailFragmentFactory;

    private InjectionComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeslot_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        View recyclerView = findViewById(R.id.reservation_list);
        assert recyclerView != null;

        component = DaggerInjectionComponent
                .builder()
                .build();
        component.inject(this); //This makes the members injected

        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.reservation_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
            TimeSlotDetailFragment fragment = timeSlotDetailFragmentFactory.getNow();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.reservation_detail_container, fragment)
                    .commit();
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclist.setClickListener(this);
        recyclist.startBind(this, new TimeSlotViewBinder(), recyclerView);
    }

    @Override
    public void showProgress() {
        Log.d(fLogTag, "showProgress");
        findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        Log.d(fLogTag, "hideProgress");
        findViewById(R.id.progressBar).setVisibility(View.GONE);
    }

    @Override
    public void showResults() {
        Log.d(fLogTag, "showResults");
        findViewById(R.id.reservation_list).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideResults() {
        Log.d(fLogTag, "hideResults");
        findViewById(R.id.reservation_list).setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        Log.d(fLogTag, "showError");
        findViewById(R.id.imageViewError).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideError() {
        Log.d(fLogTag, "hideError");
        findViewById(R.id.imageViewError).setVisibility(View.GONE);
    }

    @Override
    public void onClick(ITimeSlot item) {
        if (mTwoPane) {
            TimeSlotDetailFragment fragment = timeSlotDetailFragmentFactory.getByTimeSlot(item);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.reservation_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, ReservationDetailActivity.class);
            intent.putExtra(ReservationDetailFragment.ARG_ITEM_ID, item.getId());

            startActivity(intent);
        }
    }
}
