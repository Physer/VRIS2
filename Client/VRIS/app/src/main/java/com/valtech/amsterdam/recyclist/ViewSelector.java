package com.valtech.amsterdam.recyclist;

import android.view.View;

/**
 * Created by jasper.van.zijp on 18-7-2017.
 */

public interface ViewSelector<TModel> {
    int getViewResourceId(TModel object);
}
