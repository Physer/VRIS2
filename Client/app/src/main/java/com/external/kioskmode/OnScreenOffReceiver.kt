package com.external.kioskmode

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager


/**
 * Created by marvin.brouwer on 28-12-2017.
 */
class OnScreenOffReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        if (Intent.ACTION_SCREEN_OFF == intent.action) {
            val ctx = context.getApplicationContext() as KioskApplication
            // is Kiosk Mode active?
            if (isKioskModeActive(ctx)) {
                wakeUpDevice(ctx)
            }
        }
    }

    private fun wakeUpDevice(context: KioskApplication) {
        val wakeLock = context.getWakeLock() // get WakeLock reference via KioskApplication
        if (wakeLock.isHeld()) {
            wakeLock.release() // release old wake lock
        }

        // create a new wake lock...
        wakeLock.acquire()

        // ... and release again
        wakeLock.release()
    }

    private fun isKioskModeActive(context: Context): Boolean {
        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        return sp.getBoolean(PREF_KIOSK_MODE, false)
    }

    companion object {
        private val PREF_KIOSK_MODE = "pref_kiosk_mode"
    }
}