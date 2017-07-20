package com.valtech.amsterdam.vris.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.valtech.amsterdam.vris.R;

/**
 * Created by marvin.brouwer on 20-7-2017.
 */

abstract class BaseFragment extends Fragment {

    protected void navigateToFragment(Fragment fragment, boolean addHistory){
        BaseActivity activity = (BaseActivity)getActivity();
        activity.navigateToFragment(fragment, addHistory);
    }
}