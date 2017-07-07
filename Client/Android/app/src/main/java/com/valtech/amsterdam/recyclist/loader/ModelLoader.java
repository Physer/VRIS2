package com.jaspervz.www.recyclist.loader;

import java.io.IOException;
import java.util.List;

/**
 * A loader of models of type TModel
 */

public interface ModelLoader<TModel> {
    List<TModel> getList() throws IOException;
}
