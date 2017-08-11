package com.valtech.amsterdam.recyclist;

import android.view.View;

import com.valtech.amsterdam.vris.model.OnClickListener;

/**
 * Created by jasper.van.zijp on 26-5-2017.
 */

public interface RecyclistViewBinder<TModel> {
    void bindView(View view, TModel model, OnClickListener clickListener, int position);
}
