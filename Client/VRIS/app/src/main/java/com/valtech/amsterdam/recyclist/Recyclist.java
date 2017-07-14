package com.valtech.amsterdam.recyclist;

import android.support.v7.widget.RecyclerView;

import com.valtech.amsterdam.recyclist.loader.implementation.network.BufferedStreamContentReader;
import com.valtech.amsterdam.recyclist.loader.implementation.network.GsonDesynchronizer;
import com.valtech.amsterdam.recyclist.loader.implementation.network.NetworkModelLoader;

import java.util.List;

/**
 * Created by jasper.van.zijp on 26-5-2017.
 */

public class Recyclist<TModel> {

    private Recyclistener mListener;
    private String mBaseUrl;
    private Class<TModel> mModelClass;
    private RecyclistViewBinder<TModel> mViewBinder;
    private int mRowViewResourceId;
    private RecyclerView mRecyclerView;

    private LoadListCommand<TModel> mLoadListCommand;

    public Recyclist(LoadListCommand<TModel> loadListCommand) {
        mLoadListCommand = loadListCommand;
    }

    public void startBind(Recyclistener listener, String baseUrl, Class<TModel> modelClass, RecyclistViewBinder<TModel> viewBinder, int rowViewResourceId, RecyclerView recyclerView) {
        if (mLoadListCommand == null) onLoadError("Previous task is still running");

        mListener = listener;
        mBaseUrl = baseUrl;
        mModelClass = modelClass;
        mViewBinder = viewBinder;
        mRowViewResourceId = rowViewResourceId;
        mRecyclerView = recyclerView;

        mListener.showProgress();
        mListener.hideResults();
        mListener.hideError();

        AsyncCommandExecutor<List<TModel>> taskExecutor = new AsyncCommandExecutor<>(new TaskListener<List<TModel>>() {
            @Override
            public void onComplete(List<TModel> results) {
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

    private void onLoadComplete(List<TModel> results) {
        mLoadListCommand = null;

        RecyclistAdapter<TModel> adapter = new RecyclistAdapter<>(results, mViewBinder, mRowViewResourceId);
        mRecyclerView.setAdapter(adapter);

        mListener.hideProgress();
        mListener.showResults();
        mListener.hideError();
    }
}