package com.valtech.amsterdam.recyclist.loader.implementation.network;

import java.util.ArrayList;

/**
 * Created by jasper.van.zijp on 18-4-2017.
 */

public interface Desynchronizer<TModel> {
    ArrayList<TModel> getList(String json);
}
