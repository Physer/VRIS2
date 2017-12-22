package com.external.kioskmode

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

/**
 * Created by marvin.brouwer on 22-12-2017.
 */
class HomeWatcher(private val mContext: Context) {
    private val mFilter: IntentFilter
    private var mListener: IHomePressedListener? = null
    private var mRecevier: InnerRecevier? = null

    init {
        mFilter = IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
    }

    fun setOnHomePressedListener(listener: IHomePressedListener) {
        mListener = listener
        mRecevier = InnerRecevier()
    }

    fun startWatch() {
        if (mRecevier != null) {
            mContext.registerReceiver(mRecevier, mFilter)
        }
    }

    fun stopWatch() {
        if (mRecevier != null) {
            mContext.unregisterReceiver(mRecevier)
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
                    Log.e(TAG, "action:$action,reason:$reason")
                    if (mListener != null) {
                        if (reason == SYSTEM_DIALOG_REASON_HOME_KEY) {
                            mListener!!.onHomePressed()
                        } else if (reason == SYSTEM_DIALOG_REASON_RECENT_APPS) {
                            mListener!!.onRecentAppsPressed()
                        }
                    }
                }
            }
        }
    }

    companion object {
        internal val TAG = "hg"
    }
}