package com.valtech.amsterdam.recyclist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valtech.amsterdam.vris.ui.OnClickListener;

import java.util.List;

/**
 * Created by jasper.van.zijp on 7-4-2017.
 */

public class RecyclistAdapter<TModel> extends RecyclerView.Adapter<RecyclistAdapter.ViewHolder> {
    private List<TModel> mObjects;
    private RecyclistViewBinder<TModel> mViewBinder;
    private OnClickListener mClickListener;
    private ViewSelector<TModel> mModelViewSelector;

    public RecyclistAdapter(List<TModel> objects, RecyclistViewBinder<TModel> viewBinder, OnClickListener clickListener, ViewSelector<TModel> modelViewSelector) {
        mObjects = objects;
        mViewBinder = viewBinder;
        mClickListener = clickListener;
        mModelViewSelector = modelViewSelector;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View productView = LayoutInflater.from(parent.getContext())
                .inflate(viewType, parent, false);
        return new RecyclistAdapter.ViewHolder(productView, mViewBinder);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TModel model = mObjects.get(position);
        if (model == null) return;

        holder.getViewBinder().bindView(holder.getView(), model, mClickListener, position);
    }

    @Override
    public int getItemCount() {
        return mObjects.size();
    }

    public static class ViewHolder<TModel> extends RecyclerView.ViewHolder {
        private View mView;
        private RecyclistViewBinder<TModel> mViewBinder;

        public ViewHolder(View itemView, RecyclistViewBinder<TModel> viewBinder) {
            super(itemView);
            mView = itemView;
            mViewBinder = viewBinder;
        }

        public RecyclistViewBinder<TModel> getViewBinder() {
            return mViewBinder;
        }

        public View getView() {
            return mView;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mModelViewSelector.getViewResourceId(mObjects.get(position));
    }
}