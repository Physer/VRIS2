package com.external.kioskmode

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.PowerManager
import com.external.boot.KioskService


/**
 * Created by marvin.brouwer on 28-12-2017.
 */
abstract class KioskApplication : Application() {
    private var wakeLock: PowerManager.WakeLock? = null
    private var onScreenOffReceiver: OnScreenOffReceiver? = null

    private var _homeWatcher = HomeWatcher(this)

    override fun onCreate() {
        super.onCreate()
        registerKioskModeScreenOffReceiver()
        startKioskService()

        val self = this;

        _homeWatcher.setOnHomePressedListener(object : IHomePressedListener {
            override fun onHomePressed() = restartApp(self)
            override fun onRecentAppsPressed() = restartApp(self)
        })
        _homeWatcher.startWatch()
    }

    override fun onTerminate() {
        _homeWatcher.stopWatch()
        super.onTerminate()
    }

    private fun registerKioskModeScreenOffReceiver() {
        // register screen off receiver
        val filter = IntentFilter(Intent.ACTION_SCREEN_OFF)
        onScreenOffReceiver = OnScreenOffReceiver()
        registerReceiver(onScreenOffReceiver, filter)
    }

    fun getWakeLock(): PowerManager.WakeLock {
        if (wakeLock == null) {
            // lazy loading: first call, create wakeLock via PowerManager.
            val pm = getSystemService(Context.POWER_SERVICE) as PowerManager
            wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK or PowerManager.ACQUIRE_CAUSES_WAKEUP, "wakeup")
        }
        return wakeLock!!
    }

    private fun startKioskService() {
        startService(Intent(this, KioskService::class.java))
    }

    var ActivityContext: Activity
        get() = KioskApplication.ActivityContext
        set(value) {
            KioskApplication.ActivityContext = value
        }

    companion object {
        lateinit var ActivityContext: Activity

        fun restartApp(context: Context){
            if(ActivityContext == null) return
            ActivityContext.finish()
            context.startActivity(Intent(ActivityContext, ActivityContext::class.java))
        }

    }
}