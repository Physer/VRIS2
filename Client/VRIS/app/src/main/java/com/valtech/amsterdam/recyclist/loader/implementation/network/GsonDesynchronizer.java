package com.valtech.amsterdam.recyclist.loader.implementation.network;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaspe on 11-4-2017.
 */

public class GsonDesynchronizer<TModel> implements Desynchronizer {
    private Class<TModel> mModelClass;

    public GsonDesynchronizer(Class<TModel> modelClass) {
        mModelClass = modelClass;
    }

    public List<TModel> getList(String json)
    {
        Object [] array = (Object[])java.lang.reflect.Array.newInstance(mModelClass, 1);
        array = new Gson().fromJson(json, array.getClass());
        List<TModel> list = new ArrayList<>();
        for (Object anArray : array) list.add((TModel) anArray);
        return list;
    }
}
