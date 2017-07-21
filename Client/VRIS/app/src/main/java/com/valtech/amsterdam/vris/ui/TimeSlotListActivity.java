package com.valtech.amsterdam.vris.ui;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.PeriodicSync;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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
import com.valtech.amsterdam.vris.business.loaders.ITimeSlotLoader;
import com.valtech.amsterdam.vris.model.ITimeSlot;
import com.valtech.amsterdam.vris.business.factories.TimeSlotDetailFragmentFactory;

import org.joda.time.DateTime;

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
    @Inject
    ITimeSlotLoader timeSlotLoader;

    private InjectionComponent component;

    public static final String AUTHORITY = "com.example.android.datasync.provider";
    public static final String ACCOUNT_TYPE = "example.com";
    public static final String ACCOUNT = "dummyaccount2";
    Account mAccount;

    public static final long SECONDS_PER_MINUTE = 60L;
    public static final long SYNC_INTERVAL_IN_MINUTES = 1L;
    public static final long SYNC_INTERVAL =
            SYNC_INTERVAL_IN_MINUTES *
                    SECONDS_PER_MINUTE;

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

            ITimeSlot timeSlot = timeSlotLoader.getByTime(DateTime.now());
            if(timeSlot == null) return;
            Fragment fragment = timeSlotDetailFragmentFactory.getDetail(timeSlot);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.reservation_detail_container, fragment)
                    .commit();
        }

        mAccount = CreateSyncAccount(this);

//        if (ContentResolver.getPeriodicSyncs(mAccount, AUTHORITY).size() > 0) {
//            PeriodicSync ps = ContentResolver.getPeriodicSyncs(mAccount, AUTHORITY).get(0);
//            ContentResolver.removePeriodicSync(mAccount, AUTHORITY, Bundle.EMPTY);
//        }

        ContentResolver.addPeriodicSync(
                mAccount,
                AUTHORITY,
                Bundle.EMPTY,
                900); //Framework forces anything lower than 900 to 900

        PeriodicSync ps = ContentResolver.getPeriodicSyncs(mAccount, AUTHORITY).get(0);
        Log.d(fLogTag, Long.toString(ps.period));
    }

    /**
     * Create a new dummy account for the sync adapter
     *
     * @param context The application context
     */
    public static Account CreateSyncAccount(Context context) {
        // Create the account type and default account
        Account newAccount = new Account(
                ACCOUNT, ACCOUNT_TYPE);
        // Get an instance of the Android account manager
        AccountManager accountManager =
                (AccountManager) context.getSystemService(
                        ACCOUNT_SERVICE);
        /*
         * Add the account and account type, no password or user data
         * If successful, return the Account object, otherwise report an error.
         */
        if (accountManager.addAccountExplicitly(newAccount, null, null)) {
            /*
             * If you don't set android:syncable="true" in
             * in your <provider> element in the manifest,
             * then call context.setIsSyncable(account, AUTHORITY, 1)
             * here.
             */
            Log.d(fLogTag, "success");
            return newAccount;
        } else {
            /*
             * The account exists or some other error occurred. Log this, report it,
             * or handle it internally.
             */
            Log.d(fLogTag, "failed");
            return accountManager.getAccountsByType(ACCOUNT_TYPE)[0];
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
            Fragment fragment = timeSlotDetailFragmentFactory.getDetailOrCreate(item);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.reservation_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, ReservationDetailActivity.class);
            intent.putExtra(ReservationDetailFragment.ARG_ITEM_ID, item.getId());

            startActivity(intent);
        }

        // todo reset after time
    }
}
