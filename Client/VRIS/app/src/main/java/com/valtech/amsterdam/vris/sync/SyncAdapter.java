package com.valtech.amsterdam.vris.sync;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncResult;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.valtech.amsterdam.vris.model.TimeSlot;
import com.valtech.amsterdam.vris.ui.TimeSlotListActivity;

import org.joda.time.DateTime;

import java.util.Random;

/**
 * Created by jasper.van.zijp on 20-7-2017.
 */

public class SyncAdapter extends AbstractThreadedSyncAdapter {
    private final static String fLogTag = "SYNCADAPTER";

    ContentResolver mContentResolver;

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        Log.d(fLogTag, "SyncAdapter");
        mContentResolver = context.getContentResolver();
    }

    public SyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        Log.d(fLogTag, "SyncAdapter");
        mContentResolver = context.getContentResolver();
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        Log.d(fLogTag, "onPerformSync account: " + account + " extras: " + extras + " authority: " + authority + " provider:" + provider + " syncresult: " +syncResult);

        Random rand = new Random();
        int minute = rand.nextInt(60);

        //for(int i=0; i<2; i++) {
            TimeSlot ts = new TimeSlot(15, new DateTime(2017, 7, 24, 14, 0, 0), new DateTime(2017, 7, 24, 15, 0, 0));
            ContentValues b = ts.toContentValues();
            Uri insertedUri = mContentResolver.insert(Uri.parse("content://com.valtech.amsterdam.vris.sync.contentprovider/timeslot"), b);
            if (insertedUri != null) {
                Log.d(fLogTag, insertedUri.toString());
            }

            getContext().getContentResolver().notifyChange(Uri.parse("content://com.valtech.amsterdam.vris.sync.contentprovider/timeslot"), null, false);
        //}
    }
}