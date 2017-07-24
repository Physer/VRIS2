package com.valtech.amsterdam.vris;

import android.app.Application;

import com.valtech.amsterdam.recyclist.Recyclist;
import com.valtech.amsterdam.recyclist.Updater;
import com.valtech.amsterdam.vris.model.ITimeSlot;

/**
 * Created by jasper.van.zijp on 24-7-2017.
 */

public class CustomApplication extends Application {
    private Updater<ITimeSlot> mUpdater;

    public Updater<ITimeSlot> getUpdater() {
        return mUpdater;
    }

    public void setUpdater(Updater<ITimeSlot> updater) {
        mUpdater = updater;
    }
}
