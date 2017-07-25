package com.valtech.amsterdam.vris;

import com.valtech.amsterdam.recyclist.modifiers.Updater;
import com.valtech.amsterdam.vris.boot.AppContext;
import com.valtech.amsterdam.vris.model.ITimeSlot;

/**
 * Created by jasper.van.zijp on 24-7-2017.
 */

public final class CustomApplication extends AppContext {
    private Updater<ITimeSlot> mUpdater;

    public Updater<ITimeSlot> getUpdater() {
        return mUpdater;
    }

    public void setUpdater(Updater<ITimeSlot> updater) {
        mUpdater = updater;
    }
}
