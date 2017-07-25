package com.valtech.amsterdam.recyclist;

import com.valtech.amsterdam.recyclist.modifiers.Updater;

/**
 * Created by jasper.van.zijp on 26-5-2017.
 */

public interface Recyclistener<TModel> {
    void showProgress();
    void hideProgress();
    void showResults(Updater<TModel> updater);
    void hideResults();
    void showError(String message);
    void hideError();
}
