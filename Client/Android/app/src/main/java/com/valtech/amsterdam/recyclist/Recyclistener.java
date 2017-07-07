package com.jaspervz.www.recyclist;

/**
 * Created by jasper.van.zijp on 26-5-2017.
 */

public interface Recyclistener {
    void showProgress();
    void hideProgress();
    void showResults();
    void hideResults();
    void showError(String message);
    void hideError();
}
