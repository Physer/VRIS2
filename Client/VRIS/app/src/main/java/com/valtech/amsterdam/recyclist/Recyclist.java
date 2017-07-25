package com.valtech.amsterdam.recyclist;

import android.support.v7.widget.RecyclerView;

import com.valtech.amsterdam.recyclist.modifiers.Inserter;
import com.valtech.amsterdam.recyclist.modifiers.PositionDeterminator;
import com.valtech.amsterdam.recyclist.modifiers.Updater;
import com.valtech.amsterdam.vris.ui.OnClickListener;

import java.util.List;

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
    private List<TModel> mModelList;
    private RecyclistAdapter<TModel> mAdapter;
    private PositionDeterminator<TModel> mPositionDeterminator;
    private Inserter<TModel> mInserter;

    public Recyclist(LoadListCommand<TModel> loadListCommand, ViewSelector<TModel> modelViewSelector, RecyclistViewBinder<TModel> viewBinder, PositionDeterminator<TModel> positionDeterminator, Inserter<TModel> inserter) {
        mLoadListCommand = loadListCommand;
        mModelViewSelector = modelViewSelector;
        mViewBinder = viewBinder;
        mPositionDeterminator = positionDeterminator;
        mInserter = inserter;
    }

    public void startBind(Recyclistener listener, RecyclerView recyclerView) {
        if (mLoadListCommand == null) onLoadError("Previous task is still running");

        mListener = listener;
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

        mAdapter = new RecyclistAdapter<>(results, mViewBinder, mClickListener, mModelViewSelector);

        mModelList = results;
        mRecyclerView.setAdapter(mAdapter);

        mListener.hideProgress();
        mListener.showResults(new Updater<TModel>(results, mAdapter, mPositionDeterminator, mInserter));
        mListener.hideError();
    }

    public void setClickListener(OnClickListener clickListener) {
        mClickListener = clickListener;
    }

    public void insert(int index, TModel model) {
        mModelList.add(index, model);
    }

    public RecyclistAdapter<TModel> getAdapter() {
        return mAdapter;
    }
}