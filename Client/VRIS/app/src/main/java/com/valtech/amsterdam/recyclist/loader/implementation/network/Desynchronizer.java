package com.valtech.amsterdam.recyclist.loader.implementation.network;

import android.databinding.ObservableList;

/**
 * Created by jasper.van.zijp on 18-4-2017.
 */

public interface Desynchronizer<TModel> {
    ObservableList<TModel> getList(String json);
}
