package com.valtech.amsterdam.recyclist.loader;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A loader of models of type TModel
 */

public interface ModelLoader<TModel> {
    ArrayList<TModel> getList() throws IOException;
}
