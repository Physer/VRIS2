package com.jaspervz.www.recyclist.loader.implementation.network;

import java.util.List;

/**
 * Created by jasper.van.zijp on 18-4-2017.
 */

public interface Desynchronizer<TModel> {
    List<TModel> getList(String json);
}
