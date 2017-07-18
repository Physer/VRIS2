package com.valtech.amsterdam.vris;

import com.valtech.amsterdam.vris.ui.ReservationListActivity;

import javax.inject.Singleton;
import dagger.Component;

/**
 * Created by jasper.van.zijp on 14-7-2017.
 */

@Singleton
@Component(modules = { RecyclistModule.class })
public interface InjectionComponent {

    // provide the dependency for dependent components
    // (not needed for single-component setups)

    // allow to inject into our ReservationListActivity
    // method name not important
    void inject(ReservationListActivity main);
}