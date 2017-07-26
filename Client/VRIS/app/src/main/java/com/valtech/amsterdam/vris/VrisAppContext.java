package com.valtech.amsterdam.vris;

import com.valtech.amsterdam.recyclist.modifiers.Updater;
import com.valtech.amsterdam.vris.boot.AppContext;
import com.valtech.amsterdam.vris.model.ITimeSlot;

/**
 * Created by jasper.van.zijp on 24-7-2017.
 */

public final class VrisAppContext extends AppContext {

    private InjectionComponent applicationComponent;
    public InjectionComponent getApplicationComponent(){
        return applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerInjectionComponent
            .builder()
            .build();

    }

    private Updater<ITimeSlot> mUpdater;

    public Updater<ITimeSlot> getUpdater() {
        return mUpdater;
    }

    public void setUpdater(Updater<ITimeSlot> updater) {
        mUpdater = updater;
    }
}
