package com.valtech.amsterdam.vris.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by marvin.brouwer on 20-7-2017.
 */

public abstract class BaseFragment extends Fragment {

    protected void navigateToFragment(Fragment fragment, boolean addHistory){
        BaseActivity activity = (BaseActivity)getActivity();
        activity.navigateToFragment(fragment, addHistory);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("Fragment","onCreate");
        super.onCreate(savedInstanceState);
    }
}