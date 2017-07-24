package com.valtech.amsterdam.recyclist;

import android.databinding.Observable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;

import com.valtech.amsterdam.vris.ui.OnClickListener;

/**
 * Created by jasper.van.zijp on 26-5-2017.
 */

public class Recyclist<TModel> {

    private Recyclistener mListener;
    private RecyclistViewBinder<TModel> mViewBinder;
    private RecyclerView mRecyclerView;
    private OnClickListener mClickListener;
    private LoadListCommand<TModel> mLoadListCommand;
    private ViewSelector<TModel> mModelViewSelector;

    public Recyclist(LoadListCommand<TModel> loadListCommand, ViewSelector<TModel> modelViewSelector) {
        mLoadListCommand = loadListCommand;
        mModelViewSelector = modelViewSelector;
    }

    public void startBind(Recyclistener listener, RecyclistViewBinder<TModel> viewBinder, RecyclerView recyclerView) {
        if (mLoadListCommand == null) onLoadError("Previous task is still running");

        mListener = listener;
        mViewBinder = viewBinder;
        mRecyclerView = recyclerView;

        mListener.showProgress();
        mListener.hideResults();
        mListener.hideError();

        AsyncCommandExecutor<ObservableList<TModel>> taskExecutor = new AsyncCommandExecutor<>(new TaskListener<ObservableList<TModel>>() {
            @Override
            public void onComplete(ObservableList<TModel> results) {
                onLoadComplete(results);
            }

            @Override
            public void onError(Exception exception) {
                onLoadError(exception.getMessage());
            }
        });

        taskExecutor.execute(mLoadListCommand);
    }

    private void onLoadError(String message) {
        mLoadListCommand = null;

        mListener.hideProgress();
        mListener.hideResults();
        mListener.showError(message);
    }

    private void onLoadComplete(ObservableList<TModel> results) {
        mLoadListCommand = null;

        RecyclistAdapter<TModel> adapter = new RecyclistAdapter<>(results, mViewBinder, mClickListener, mModelViewSelector);
        results.addOnListChangedCallback(new RecyclistDataChangedCallback(mListener, adapter));
        mRecyclerView.setAdapter(adapter);

        mListener.hideProgress();
        mListener.showResults();
        mListener.hideError();

    }

    public void setClickListener(OnClickListener clickListener) {
        mClickListener = clickListener;
    }
}