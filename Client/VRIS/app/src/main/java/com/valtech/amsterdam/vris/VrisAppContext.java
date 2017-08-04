package com.valtech.amsterdam.vris;

import android.content.Intent;

import com.valtech.amsterdam.recyclist.modifiers.Updater;
import com.external.boot.AppContext;
import com.valtech.amsterdam.vris.model.ITimeSlot;

/**
 * Created by jasper.van.zijp on 24-7-2017.
 */

public final class VrisAppContext extends AppContext {
    public static final String INACTIVITY_BROADCAST = "com.valtech.amsterdam.vris.INACTIVITY_BROADCAST";

    private InjectionComponent applicationComponent;
    public InjectionComponent getApplicationComponent(){
        return applicationComponent;
    }

    private Updater<ITimeSlot> mUpdater;

    public Updater<ITimeSlot> getUpdater() {
        return mUpdater;
    }

    public void setUpdater(Updater<ITimeSlot> updater) {
        mUpdater = updater;
    }

    public void broadCastInactivity(){
        Intent i = new Intent(INACTIVITY_BROADCAST);
        sendBroadcast(i);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerInjectionComponent
                .builder()
                .build();
    }
}
