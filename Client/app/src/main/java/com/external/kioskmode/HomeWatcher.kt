package com.external.kioskmode

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

/**
 * Created by marvin.brouwer on 22-12-2017.
 */
class HomeWatcher(private val context: Context) {
    private val filter: IntentFilter
    private var listener: IHomePressedListener? = null
    private var recevier: InnerRecevier? = null

    init {
        filter = IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
    }

    fun setOnHomePressedListener(listener: IHomePressedListener) {
        this.listener = listener
        recevier = InnerRecevier()
    }

    fun startWatch() {
        if (recevier != null) {
            context.registerReceiver(recevier, filter)
        }
    }

    fun stopWatch() {
        if (recevier != null) {
            context.unregisterReceiver(recevier)
        }
    }

    internal inner class InnerRecevier : BroadcastReceiver() {
        val SYSTEM_DIALOG_REASON_KEY = "reason"
        val SYSTEM_DIALOG_REASON_GLOBAL_ACTIONS = "globalactions"
        val SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps"
        val SYSTEM_DIALOG_REASON_HOME_KEY = "homekey"

        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (action == Intent.ACTION_CLOSE_SYSTEM_DIALOGS) {
                val reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY)
                if (reason != null) {
                    Log.w("HomeWatcher", "action:$action,reason:$reason")
                    if (listener != null) {
                        if (reason == SYSTEM_DIALOG_REASON_HOME_KEY) {
                            listener!!.onHomePressed()
                        } else if (reason == SYSTEM_DIALOG_REASON_RECENT_APPS) {
                            listener!!.onRecentAppsPressed()
                        }
                    }
                }
            }
        }
    }
}