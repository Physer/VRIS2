package com.jaspervz.www.recyclist;

import com.jaspervz.www.recyclist.loader.ModelLoader;

import java.io.IOException;
import java.util.List;

/**
 * A Commands that loads a List of objects of type TModel
 */

public class LoadListCommand<TModel> implements Command<List<TModel>> {
    private ModelLoader<TModel> mModelLoader;

    public LoadListCommand(ModelLoader<TModel> modelLoader) {
        mModelLoader = modelLoader;
    }

    @Override
    public List<TModel> execute() throws IOException {
        return mModelLoader.getList();
    }
}