package com.valtech.amsterdam.vris;

import com.valtech.amsterdam.recyclist.LoadListCommand;
import com.valtech.amsterdam.recyclist.Recyclist;
import com.valtech.amsterdam.recyclist.ViewSelector;
import com.valtech.amsterdam.recyclist.loader.ModelLoader;
import com.valtech.amsterdam.recyclist.loader.implementation.network.BufferedStreamContentReader;
import com.valtech.amsterdam.recyclist.loader.implementation.network.GsonDesynchronizer;
import com.valtech.amsterdam.recyclist.loader.implementation.network.NetworkModelLoader;
import com.valtech.amsterdam.vris.business.TimeSlotLoader;
import com.valtech.amsterdam.vris.dummy.DummyModelErrorLoader;
import com.valtech.amsterdam.vris.dummy.DummyModelLoader;
import com.valtech.amsterdam.vris.dummy.DummyTimeSlotLoader;
import com.valtech.amsterdam.vris.model.Reservation;
import com.valtech.amsterdam.vris.model.TimeSlot;
import com.valtech.amsterdam.vris.ui.TimeSlotViewSelector;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jasper.van.zijp on 14-7-2017.
 */

@Module
public class RecyclistModule {
    @Provides
    @Singleton
    Recyclist<TimeSlot> getReservationRecyclist(LoadListCommand<TimeSlot> loadListCommand, ViewSelector<TimeSlot> viewSelector){
        return new Recyclist<>(loadListCommand, viewSelector);
    }

    @Provides
    @Singleton
    LoadListCommand<TimeSlot> getReservationLoadListCommand(@Named("DummyTimeSlotLoader")ModelLoader<TimeSlot> modelLoader) {
        return new LoadListCommand<>(modelLoader);
    }

    @Provides
    @Named("DummyTimeSlotLoader")
    @Singleton
    ModelLoader<TimeSlot> getDummyTimeSlotLoadListCommand(@Named("DummyModelLoader") ModelLoader<Reservation> modelLoader) {
        return new DummyTimeSlotLoader(modelLoader);
    }

    @Provides
    @Singleton
    ModelLoader<TimeSlot> getTimeSlotLoadListCommand(@Named("DummyModelLoader") ModelLoader<Reservation> modelLoader) {
        return new TimeSlotLoader(modelLoader);
    }

    @Provides
    @Named("DummyModelLoader")
    @Singleton
    ModelLoader<Reservation> getDummyModelLoader() {
        return new DummyModelLoader();
    }

    @Provides
    @Named("DummyModelErrorLoader")
    @Singleton
    ModelLoader<Reservation> getDummyErrorModelLoader() {
        return new DummyModelErrorLoader();
    }

    @Provides
    @Singleton
    ModelLoader<Reservation> getProductModelLoader(@Named("apiUrl") String serverUrl) {
        return new NetworkModelLoader<>(
                new BufferedStreamContentReader(),
                new GsonDesynchronizer<>(Reservation.class),
                Reservation.class,
                serverUrl);
    }

    @Provides
    @Named("apiUrl")
    String provideApiUrl() {
        return "http://dev-capi.azurewebsites.net/api/";
    }

    @Provides
    @Singleton
    ViewSelector<TimeSlot> getViewSelector() {
        return new TimeSlotViewSelector();
    }
}
