package com.valtech.amsterdam.recyclist;

import android.databinding.ObservableList;

/**
 * Created by marvin.brouwer on 24-7-2017.
 */

public final class RecyclistDataChangedCallback<TModel> extends ObservableList.OnListChangedCallback {

    private final Recyclistener listener;
    private final RecyclistAdapter<TModel> adapter;

    public RecyclistDataChangedCallback(Recyclistener listener, RecyclistAdapter<TModel> adapter){
        this.listener = listener;
        this.adapter = adapter;
    }
    private void invokeChange(){
        listener.showProgress();
        adapter.notifyDataSetChanged();
        listener.showResults();
        listener.hideProgress();
    }

    @Override
    public void onChanged(ObservableList sender) { invokeChange(); }

    @Override
    public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount) { invokeChange(); }

    @Override
    public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) { invokeChange(); }

    @Override
    public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition, int itemCount)  { invokeChange(); }

    @Override
    public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) { invokeChange(); }
}
