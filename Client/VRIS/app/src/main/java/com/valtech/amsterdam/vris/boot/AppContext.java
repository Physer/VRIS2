package com.valtech.amsterdam.vris.boot;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;

import com.valtech.amsterdam.vris.BuildConfig;

/**
 * Created by marvin.brouwer on 20-7-2017.
 */

public final class AppContext extends Application {
    private AppContext instance;
    private PowerManager.WakeLock wakeLock;
    private OnScreenOffReceiver onScreenOffReceiver;
    private boolean monitorKioskMode = BuildConfig.KIOSK_MODE;


    @Override
    public void onCreate() {
        super.onCreate();
        if(!monitorKioskMode) return;
        instance = this;
        registerKioskModeScreenOffReceiver();
        startKioskService();
    }

    private void registerKioskModeScreenOffReceiver() {
        // register screen off receiver
        final IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        onScreenOffReceiver = new OnScreenOffReceiver();
        registerReceiver(onScreenOffReceiver, filter);
    }

    public PowerManager.WakeLock getWakeLock() {
        if(wakeLock == null) {
            // lazy loading: first call, create wakeLock via PowerManager.
            PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "wakeup");
        }
        return wakeLock;
    }

    private void startKioskService() {
        startService(new Intent(this, KioskService.class));
    }
}