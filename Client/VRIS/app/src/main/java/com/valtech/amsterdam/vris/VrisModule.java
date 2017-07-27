package com.valtech.amsterdam.vris;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;

import com.valtech.amsterdam.recyclist.LoadListCommand;
import com.valtech.amsterdam.recyclist.modifiers.Inserter;
import com.valtech.amsterdam.recyclist.modifiers.PositionDeterminator;
import com.valtech.amsterdam.recyclist.Recyclist;
import com.valtech.amsterdam.recyclist.RecyclistViewBinder;
import com.valtech.amsterdam.recyclist.ViewSelector;
import com.valtech.amsterdam.recyclist.loader.ModelLoader;
import com.valtech.amsterdam.recyclist.loader.implementation.network.BufferedStreamContentReader;
import com.valtech.amsterdam.recyclist.loader.implementation.network.GsonDesynchronizer;
import com.valtech.amsterdam.recyclist.loader.implementation.network.NetworkModelLoader;
import com.valtech.amsterdam.vris.business.listers.TimeSlotLister;
import com.valtech.amsterdam.vris.business.loaders.ITimeSlotLoader;
import com.valtech.amsterdam.vris.business.loaders.TimeSlotLoader;
import com.valtech.amsterdam.vris.business.services.navigation.INavigationService;
import com.valtech.amsterdam.vris.business.services.navigation.NavigationService;
import com.valtech.amsterdam.vris.dummy.DummyModelErrorLoader;
import com.valtech.amsterdam.vris.dummy.DummyModelLoader;
import com.valtech.amsterdam.vris.dummy.DummyTimeSlotLoader;
import com.valtech.amsterdam.vris.model.ITimeSlot;
import com.valtech.amsterdam.vris.model.Reservation;
import com.valtech.amsterdam.vris.business.factories.TimeSlotDetailFragmentFactory;
import com.valtech.amsterdam.vris.ui.TimeSlotViewBinder;
import com.valtech.amsterdam.vris.viewSelectors.TimeSlotItemViewSelector;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jasper.van.zijp on 14-7-2017.
 */

@Module
public class VrisModule {
    @Provides
    @Singleton
    Recyclist<ITimeSlot> getReservationRecyclist(LoadListCommand<ITimeSlot> loadListCommand,
                                                 ViewSelector<ITimeSlot> viewSelector,
                                                 RecyclistViewBinder<ITimeSlot> viewBinder,
                                                 @Named("FixedOnePositionDeterminator") PositionDeterminator<ITimeSlot> positionDeterminator,
                                                 @Named("DefaultInserter") Inserter<ITimeSlot> inserter){
        return new Recyclist<>(loadListCommand, viewSelector, viewBinder, positionDeterminator, inserter);
    }

    @Provides
    @Singleton
    RecyclistViewBinder<ITimeSlot> getRecyclistViewBinder() {
        return new TimeSlotViewBinder();
    }

    @Provides
    @Named("RandomPositionDeterminator")
    @Singleton
    PositionDeterminator<ITimeSlot> getRandomPositionDeterminator() {
        return new PositionDeterminator<ITimeSlot>() {
            @Override
            public int getPosition(List<ITimeSlot> list, ITimeSlot objectToInsert) {
                Log.d("RANDPMPOSDET", "getPosition");
                Random rand = new Random();
                return rand.nextInt(list.size() + 1);
            }
        };
    }

    @Provides
    @Named("FixedOnePositionDeterminator")
    @Singleton
    PositionDeterminator<ITimeSlot> getFixedOnePositionDeterminator() {
        return new PositionDeterminator<ITimeSlot>() {
            @Override
            public int getPosition(List<ITimeSlot> list, ITimeSlot objectToInsert) {
                Log.d("RANDPMPOSDET", "getPosition");
                return 1;
            }
        };
    }

    @Provides
    @Named("DefaultInserter")
    @Singleton
    Inserter<ITimeSlot> getDefaultInserter() {
        return new Inserter<ITimeSlot>() {
            @Override
            public boolean insert(List<ITimeSlot> list, ITimeSlot objectToInsert, int position) {
                Log.d("DEFAULTINSERTER", "insert: " + " position: " + position + objectToInsert);
                list.add(position, objectToInsert);
                return true;
            }
        };
    }

    @Provides
    @Singleton
    LoadListCommand<ITimeSlot> getReservationLoadListCommand(@Named("DummyTimeSlotLoader")ModelLoader<ITimeSlot> modelLoader) {
        return new LoadListCommand<>(modelLoader);
    }

    @Provides
    @Named("DummyTimeSlotLoader")
    @Singleton
    ModelLoader<ITimeSlot> getDummyTimeSlotLoadListCommand(@Named("DummyModelLoader") ModelLoader<Reservation> modelLoader) {
        return new DummyTimeSlotLoader(modelLoader);
    }

    @Provides
    @Singleton
    ModelLoader<ITimeSlot> getTimeSlotLoadListCommand(@Named("DummyModelLoader") ModelLoader<Reservation> modelLoader) {
        return new TimeSlotLister(modelLoader);
    }

    @Provides
    @Singleton
    ITimeSlotLoader getTimeSlotLoaderCommand(@Named("DummyTimeSlotLoader") ModelLoader<ITimeSlot> modelLoader) {
        return new TimeSlotLoader(modelLoader);
    }

    @Provides
    @Singleton
    TimeSlotDetailFragmentFactory getTimeSlotDetailFragmentFactoryCommand() {
        return new TimeSlotDetailFragmentFactory();
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
    ViewSelector<ITimeSlot> getViewSelector() {
        return new TimeSlotItemViewSelector();
    }

    @Provides
    @Singleton
    INavigationService getNavigationService(ITimeSlotLoader timeSlotLoader, TimeSlotDetailFragmentFactory timeSlotDetailFragmentFactory){
        return new NavigationService(timeSlotLoader, timeSlotDetailFragmentFactory);
    }
}
