package com.external.boot

import android.app.ActivityManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.external.kioskmode.KioskApplication
import java.util.concurrent.TimeUnit



/**
 * Created by marvin.brouwer on 20-7-2017.
 */

class KioskService : Service() {

    private var t: Thread? = null
    private var ctx: Context? = null
    private var running = false

    private val isInBackground: Boolean
        get() {
            val am = ctx!!.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

            val taskInfo = am.getRecentTasks(1, ActivityManager.RECENT_WITH_EXCLUDED)
            val componentInfo = taskInfo[0].topActivity
            Log.d("top process", componentInfo.packageName)
            return ctx!!.applicationContext.packageName != componentInfo.packageName
        }

    override fun onDestroy() {
        Log.i(TAG, "Stopping service 'KioskService'")
        running = false
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.i(TAG, "Starting service 'KioskService'")
        running = true
        ctx = this

        // start a thread that periodically checks if your app is in the foreground
        t = Thread(Runnable {
            do {
                handleKioskMode()
                try {
                    Thread.sleep(INTERVAL)
                } catch (e: InterruptedException) {
                    Log.i(TAG, "Thread interrupted: 'KioskService'")
                }

            } while (running)
            stopSelf()
        })

        t!!.start()
        return Service.START_NOT_STICKY
    }

    private fun handleKioskMode() {
        if(!isInBackground) return
        KioskApplication.restartApp(this)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    companion object {
        private val INTERVAL = TimeUnit.MILLISECONDS.toMillis(500) // periodic interval to check in seconds -> .2 seconds
        private val TAG = KioskService::class.java.simpleName
        private val PREF_KIOSK_MODE = "pref_kiosk_mode"
    }
}
