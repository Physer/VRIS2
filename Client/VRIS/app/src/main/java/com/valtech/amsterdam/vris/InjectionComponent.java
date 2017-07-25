package com.valtech.amsterdam.vris;

import com.valtech.amsterdam.vris.ui.NewTimeSlotFragment;
import com.valtech.amsterdam.vris.ui.ReservationDetailFragment;
import com.valtech.amsterdam.vris.ui.TimeSlotDetailFragment;
import com.valtech.amsterdam.vris.ui.TimeSlotListActivity;

import javax.inject.Singleton;
import dagger.Component;

/**
 * Created by jasper.van.zijp on 14-7-2017.
 */

@Singleton
@Component(modules = { VrisModule.class })
public interface InjectionComponent {

    // provide the dependency for dependent components
    // (not needed for single-component setups)

    // allow to inject into our TimeSlotListActivity
    // method name not important
    void inject(TimeSlotListActivity main);
    void inject(NewTimeSlotFragment main);
    void inject(TimeSlotDetailFragment main);
    void inject(ReservationDetailFragment main);
}