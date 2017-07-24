package com.valtech.amsterdam.recyclist;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.valtech.amsterdam.recyclist.loader.ModelLoader;

import java.io.IOException;

/**
 * A Commands that loads a List of objects of type TModel
 */

public class LoadListCommand<TModel> implements Command<ObservableList<TModel>> {
    private ModelLoader<TModel> mModelLoader;

    public LoadListCommand(ModelLoader<TModel> modelLoader) {
        mModelLoader = modelLoader;
    }

    @Override
    public ObservableList<TModel> execute() throws IOException {
        return mModelLoader.getList();
    }
}