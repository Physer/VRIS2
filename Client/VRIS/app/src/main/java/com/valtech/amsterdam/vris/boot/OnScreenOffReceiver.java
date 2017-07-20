package com.valtech.amsterdam.vris.boot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PowerManager;
import android.preference.PreferenceManager;

import com.valtech.amsterdam.vris.BuildConfig;

/**
 * Created by marvin.brouwer on 20-7-2017.
 */

public final class OnScreenOffReceiver extends BroadcastReceiver {
    private static final String PREF_KIOSK_MODE = "pref_kiosk_mode";
    private boolean monitorKioskMode = BuildConfig.KIOSK_MODE;

    @Override
    public void onReceive(Context context, Intent intent) {
        if(!monitorKioskMode) return;

        if(Intent.ACTION_SCREEN_OFF.equals(intent.getAction())){
            AppContext ctx = (AppContext) context.getApplicationContext();
            // is Kiosk Mode active?
            if(isKioskModeActive(ctx)) {
                wakeUpDevice(ctx);
            }
        }
    }

    private void wakeUpDevice(AppContext context) {
        PowerManager.WakeLock wakeLock = context.getWakeLock(); // get WakeLock reference via AppContext
        if (wakeLock.isHeld()) {
            wakeLock.release(); // release old wake lock
        }

        // create a new wake lock...
        wakeLock.acquire();

        // ... and release again
        wakeLock.release();
    }

    private boolean isKioskModeActive(final Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(PREF_KIOSK_MODE, false);
    }
}
