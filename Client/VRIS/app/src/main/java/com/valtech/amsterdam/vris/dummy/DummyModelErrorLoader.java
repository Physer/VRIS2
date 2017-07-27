package com.valtech.amsterdam.vris.dummy;

import android.databinding.ObservableArrayList;

import com.valtech.amsterdam.recyclist.loader.ModelLoader;
import com.valtech.amsterdam.vris.model.Reservation;

import java.io.IOException;
import java.util.List;

/**
 * Created by jasper.van.zijp on 14-7-2017.
 */

public class DummyModelErrorLoader implements ModelLoader<Reservation> {
    @Override
    public List<Reservation> getList() throws IOException {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        throw new IOException("test error");
    }
}
