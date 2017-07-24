package com.valtech.amsterdam.recyclist.loader;

import android.databinding.ObservableList;

import java.io.IOException;

/**
 * A loader of models of type TModel
 */

public interface ModelLoader<TModel> {
    ObservableList<TModel> getList() throws IOException;
}
