package com.valtech.amsterdam.vris.ui;

import com.valtech.amsterdam.recyclist.modifiers.Updater;
import com.valtech.amsterdam.vris.VrisAppContext;
import com.external.boot.KioskActivity;
import com.valtech.amsterdam.vris.model.ITimeSlot;

/**
 * Created by marvin.brouwer on 20-7-2017.
 */

public abstract class BaseActivity extends KioskActivity {

    protected void setUpdater(Updater<ITimeSlot> updater) {
        ((VrisAppContext)getApplication()).setUpdater(updater);
    }

    protected Updater<ITimeSlot> getUpdater() {
        return ((VrisAppContext)getApplication()).getUpdater();
    }
}