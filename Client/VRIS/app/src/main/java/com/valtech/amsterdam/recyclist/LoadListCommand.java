package com.valtech.amsterdam.recyclist;

import com.valtech.amsterdam.recyclist.loader.ModelLoader;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A Commands that loads a List of objects of type TModel
 */

public class LoadListCommand<TModel> implements Command<ArrayList<TModel>> {
    private ModelLoader<TModel> mModelLoader;

    public LoadListCommand(ModelLoader<TModel> modelLoader) {
        mModelLoader = modelLoader;
    }

    @Override
    public ArrayList<TModel> execute() throws IOException {
        return mModelLoader.getList();
    }
}