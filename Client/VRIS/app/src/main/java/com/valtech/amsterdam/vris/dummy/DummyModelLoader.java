package com.valtech.amsterdam.vris.dummy;

import com.valtech.amsterdam.recyclist.loader.ModelLoader;
import com.valtech.amsterdam.vris.model.Reservation;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by jasper.van.zijp on 14-7-2017.
 */

public class DummyModelLoader implements ModelLoader<Reservation> {
    @Override
    public ArrayList<Reservation> getList() throws IOException {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return DummyContent.RESERVATIONS;
    }
}