package com.valtech.amsterdam.vris.boot;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.valtech.amsterdam.vris.ui.TimeSlotListActivity;

/**
 * Created by marvin.brouwer on 20-7-2017.
 */

public final class BootReceiver  extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent myIntent = new Intent(context, TimeSlotListActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(myIntent);
    }
}