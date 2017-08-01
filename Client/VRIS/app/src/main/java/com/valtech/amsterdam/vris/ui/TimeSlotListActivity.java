package com.valtech.amsterdam.vris.ui;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


import com.external.homewatcher.HomeWatcher;
import com.external.homewatcher.OnHomePressedListener;
import com.valtech.amsterdam.recyclist.Recyclist;
import com.valtech.amsterdam.recyclist.Recyclistener;
import com.valtech.amsterdam.recyclist.modifiers.Updater;
import com.valtech.amsterdam.vris.VrisAppContext;
import com.valtech.amsterdam.vris.R;
import com.valtech.amsterdam.vris.business.services.navigation.INavigationService;
import com.valtech.amsterdam.vris.model.ITimeSlot;
import com.valtech.amsterdam.vris.business.factories.TimeSlotDetailFragmentFactory;
import com.valtech.amsterdam.vris.model.OnClickListener;

import org.joda.time.DateTime;

import javax.inject.Inject;

/**
 * An activity representing a list of Reservations. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */

// todo: Custom keyboard(don't opverlap list) https://stackoverflow.com/questions/33077300/android-custom-soft-keyboard-set-keyboard-width-to-match-parent-device-screen
// https://code.tutsplus.com/tutorials/create-a-custom-keyboard-on-android--cms-22615
public class TimeSlotListActivity extends BaseActivity implements Recyclistener<ITimeSlot>, OnClickListener {
    private final static String fLogTag = "TimeSlotListActivity";
    private BroadcastReceiver _broadcastReceiver;
    private DateTime acitvityTimeStamp;

    @Inject
    Recyclist<ITimeSlot> recyclist;
    @Inject
    TimeSlotDetailFragmentFactory timeSlotDetailFragmentFactory;
    @Inject
    INavigationService navigationService;

    public static final String AUTHORITY = "com.valtech.amsterdam.vris.sync.contentprovider";
    public static final String ACCOUNT_TYPE = "com.valtech.amsterdam.vris.sync";
    public static final String ACCOUNT = "dummyaccount2";
    Account mAccount;

    public static final int MINUTE_IDLE_TIME = 5;
    public static final long SECONDS_PER_MINUTE = 60L;
    public static final long SYNC_INTERVAL_IN_MINUTES = 15L;
    public static final long SYNC_INTERVAL =
            SYNC_INTERVAL_IN_MINUTES *
                    SECONDS_PER_MINUTE;

    private ContentObserver mObserver;
    private RecyclerView recyclerView;
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeslot_list);
        ((VrisAppContext)getApplicationContext()).getApplicationComponent().inject(this); //This makes the members injected
        navigationService.setCurrentActivity(this);

        if(savedInstanceState == null) {
            recyclerView = (RecyclerView) findViewById(R.id.reservation_list);
            assert recyclerView != null;
            setupRecyclerView(recyclerView);

            mObserver = new ContentObserver(new Handler(Looper.getMainLooper())) {
                public void onChange(boolean selfChange) {
                    Log.d(fLogTag, "ContentObserver.onChange");
                    ((VrisAppContext) getApplication()).getUpdater().notifyItemInserted();
                }
            };
            getContentResolver().registerContentObserver(Uri.parse("content://com.valtech.amsterdam.vris.sync.contentprovider/timeslot"), false, mObserver);

            mAccount = CreateSyncAccount(this);

            ContentResolver.addPeriodicSync(
                    mAccount,
                    AUTHORITY,
                    Bundle.EMPTY,
                    SYNC_INTERVAL); //Framework forces anything lower than 900 to 900
        }
    }

    @Override
    public void onStop() {
        startActivity(new Intent(this, TimeSlotListActivity.class));
        try {
            if (_broadcastReceiver != null)
                unregisterReceiver(_broadcastReceiver);
        }
        catch (Exception e) { /*care*/ }
        super.onStop();
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        acitvityTimeStamp = DateTime.now();
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

    public void buttonclick(View sender) {
        Bundle settingsBundle = new Bundle();
        settingsBundle.putBoolean(
                ContentResolver.SYNC_EXTRAS_MANUAL, true);
        settingsBundle.putBoolean(
                ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        /*
         * Request the sync for the default account, authority, and
         * manual sync settings
         */
        ContentResolver.requestSync(mAccount, AUTHORITY, settingsBundle);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclist.setClickListener(this);
        recyclist.startBind(this, recyclerView);
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

    private void HideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        View focusView = getCurrentFocus();
        if(focusView != null) inputMethodManager.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
    }

    @Override
    public void showResults(Updater<ITimeSlot> updater) {
        Log.d(fLogTag, "showResults");
        findViewById(R.id.reservation_list).setVisibility(View.VISIBLE);
        setUpdater(updater);
        navigationService.setTimeSlotUpdater(updater);
        navigationService.forceNavigateToHomeSlot(true);
        HideKeyboard();

        final TimeSlotListActivity timeSlotListActivity = this;
        HomeWatcher mHomeWatcher = new HomeWatcher(this);
        mHomeWatcher.setOnHomePressedListener(new OnHomePressedListener() {
            @Override
            public void onHomePressed() {
                navigationService.navigateToHomeSlot();
                recyclerView.getLayoutManager().scrollToPosition(0);
                HideKeyboard();
            }
            @Override
            public void onRecentAppsPressed() {
                // todo: We might want to do somehting easter eggy (it's impossible to navigate to home neatly)
                startActivity(new Intent(timeSlotListActivity, TimeSlotListActivity.class));
                HideKeyboard();
            }
        });
        mHomeWatcher.startWatch();

        acitvityTimeStamp = DateTime.now();
        _broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context ctx, Intent intent) {
                if (intent.getAction().compareTo(Intent.ACTION_TIME_TICK) == 0){
                    DateTime currentDateTime = DateTime.now();
                    if(currentDateTime.isAfter(acitvityTimeStamp.plusMinutes(MINUTE_IDLE_TIME))){
                        navigationService.navigateToHomeSlot();
                        recyclerView.getLayoutManager().scrollToPosition(0);
                        Log.i("User is idle", acitvityTimeStamp.toString() + " -> " + currentDateTime.toString());
                        acitvityTimeStamp = DateTime.now();
                        HideKeyboard();
                    }else{
                        Log.i("User is not idle", acitvityTimeStamp.toString() + " -> " + currentDateTime.toString());
                    }
                }
            }
        };
        registerReceiver(_broadcastReceiver, new IntentFilter(Intent.ACTION_TIME_TICK));
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
    public void onClick(ITimeSlot item, int position) {
        if(item.getSelected() == true) return;
        navigationService.navigateToTimeSlot(item);
        recyclerView.getLayoutManager().scrollToPosition(position);
    }

    @Override
    public void onBackPressed() {
        navigationService.navigateToHomeSlot();
        recyclerView.getLayoutManager().scrollToPosition(0);
    }

}
