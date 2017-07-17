package com.valtech.amsterdam.vris;

import com.valtech.amsterdam.recyclist.LoadListCommand;
import com.valtech.amsterdam.recyclist.Recyclist;
import com.valtech.amsterdam.recyclist.loader.ModelLoader;
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
    Recyclist<Reservation> getProductRecyclist(LoadListCommand<Reservation> loadListCommand){
        return new Recyclist<>(loadListCommand);
    }

    @Provides
    @Singleton
    LoadListCommand<Reservation> getProductLoadListCommand(ModelLoader<Reservation> modelLoader) {
        return new LoadListCommand<>(modelLoader);
    }

    @Provides
    @Singleton
    ModelLoader<Reservation> getProductModelLoader() {
        return new DummyModelLoader();
    }


//    @Provides
//    @Singleton
//    ModelLoader<Reservation> getProductModelLoader(@Named("apiUrl") String serverUrl) {
//        return new NetworkModelLoader<>(
//                new BufferedStreamContentReader(),
//                new GsonDesynchronizer<>(Reservation.class),
//                Reservation.class,
//                serverUrl);
//    }
//
//    @Provides
//    @Named("apiUrl")
//    String provideApiUrl() {
//        return "http://dev-capi.azurewebsites.net/api/";
//    }
}
