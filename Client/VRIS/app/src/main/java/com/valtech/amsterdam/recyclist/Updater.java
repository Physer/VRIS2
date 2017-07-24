package com.valtech.amsterdam.recyclist;

import android.util.Log;

import java.util.List;
import java.util.Random;

/**
 * Created by jasper.van.zijp on 24-7-2017.
 */

public class Updater<TModel> {
    private final static String fLogTag = "UPDATER";
    private int mLastInserted;

    private List<TModel> mObjects;
    private RecyclistAdapter<TModel> mAdapter;

    public Updater(List<TModel> objects, RecyclistAdapter<TModel> adapter) {
        mObjects = objects;
        mAdapter = adapter;
    }

    public int add(TModel object) {
        Log.d(fLogTag, "add");

        Random rand = new Random();
        int position = rand.nextInt(mObjects.size());
        Log.d(fLogTag, "add position: " + position);

        mObjects.add(position, object);
        mLastInserted = position;

        return position;
    }

    public void notifyItemInserted() {
        mAdapter.notifyItemInserted(mLastInserted);
    }
}
