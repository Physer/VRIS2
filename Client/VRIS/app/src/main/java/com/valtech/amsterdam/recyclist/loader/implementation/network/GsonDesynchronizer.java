package com.valtech.amsterdam.recyclist.loader.implementation.network;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.google.gson.Gson;

/**
 * Created by jaspe on 11-4-2017.
 */

public class GsonDesynchronizer<TModel> implements Desynchronizer {
    private Class<TModel> mModelClass;

    public GsonDesynchronizer(Class<TModel> modelClass) {
        mModelClass = modelClass;
    }

    public ObservableList<TModel> getList(String json)
    {
        Object [] array = (Object[])java.lang.reflect.Array.newInstance(mModelClass, 1);
        array = new Gson().fromJson(json, array.getClass());
        ObservableList<TModel> list = new ObservableArrayList<>();
        for (Object anArray : array) list.add((TModel) anArray);
        return list;
    }
}
