package com.valtech.amsterdam.vris.ui;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.valtech.amsterdam.recyclist.modifiers.Updater;
import com.valtech.amsterdam.vris.VrisAppContext;
import com.valtech.amsterdam.vris.R;
import com.valtech.amsterdam.vris.model.ITimeSlot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by marvin.brouwer on 20-7-2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private final List blockedKeys = new ArrayList(Arrays.asList(KeyEvent.KEYCODE_VOLUME_DOWN, KeyEvent.KEYCODE_VOLUME_UP));

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (blockedKeys.contains(event.getKeyCode())) {
            return true;
        } else {
            return super.dispatchKeyEvent(event);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(!hasFocus && getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Close every kind of system dialog
            Intent closeDialog = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
            sendBroadcast(closeDialog);
        }
        View activityLayout = findViewById(R.id.activity_layout);
        if(activityLayout == null) return;
        activityLayout.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // This should prevent on create form beiing invoked on configuration changed
        setContentView(R.layout.activity_timeslot_list);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT) {
            return;
        }
        startActivity(new Intent(this, TimeSlotListActivity.class));
    }

    @Override
    protected void onPause() {
        if(getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT) {
            super.onPause();
            return;
        }

        ActivityManager activityManager = (ActivityManager) getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);

        activityManager.moveTaskToFront(getTaskId(), 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Activity", "onCreate");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onBackPressed() {
        // Disable back on home screen
        View page2layout = findViewById(R.id.timeslot_detail);
        if(page2layout != null && page2layout.getVisibility() == View.VISIBLE) return;

        super.onBackPressed();
    }

    protected void navigateToFragment(Fragment fragment, boolean addHistory){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        fragmentTransaction.replace(R.id.reservation_detail_container, fragment);
        fragmentTransaction.replace(R.id.reservation_detail_container, fragment);

        fragmentTransaction.commitAllowingStateLoss();
        if(addHistory) fragmentTransaction.addToBackStack(null);
    }

    protected void setUpdater(Updater<ITimeSlot> updater) {
        ((VrisAppContext)getApplication()).setUpdater(updater);
    }

    protected Updater<ITimeSlot> getUpdater() {
        return ((VrisAppContext)getApplication()).getUpdater();
    }

}