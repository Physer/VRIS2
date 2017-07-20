package com.valtech.amsterdam.vris.ui;

import android.support.v4.app.Fragment;

/**
 * Created by marvin.brouwer on 20-7-2017.
 */

abstract class BaseFragment extends Fragment {

    protected void navigateToFragment(Fragment fragment, boolean addHistory){
        BaseActivity activity = (BaseActivity)getActivity();
        activity.navigateToFragment(fragment, addHistory);
    }
}