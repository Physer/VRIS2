package com.valtech.amsterdam.vris;

import com.valtech.amsterdam.recyclist.LoadListCommand;
import com.valtech.amsterdam.recyclist.Recyclist;
import com.valtech.amsterdam.recyclist.loader.ModelLoader;
import com.valtech.amsterdam.recyclist.loader.implementation.network.BufferedStreamContentReader;
import com.valtech.amsterdam.recyclist.loader.implementation.network.GsonDesynchronizer;
import com.valtech.amsterdam.recyclist.loader.implementation.network.NetworkModelLoader;
import com.valtech.amsterdam.vris.dummy.DummyModelErrorLoader;
import com.valtech.amsterdam.vris.dummy.DummyModelLoader;
import com.valtech.amsterdam.vris.model.Reservation;

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
    Recyclist<Reservation> getReservationRecyclist(LoadListCommand<Reservation> loadListCommand){
        return new Recyclist<>(loadListCommand);
    }

    @Provides
    @Singleton
    LoadListCommand<Reservation> getReservationLoadListCommand(@Named("DummyModelLoader") ModelLoader<Reservation> modelLoader) {
        return new LoadListCommand<>(modelLoader);
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
}
