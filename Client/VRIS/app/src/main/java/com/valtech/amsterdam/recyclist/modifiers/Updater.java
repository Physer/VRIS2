package com.valtech.amsterdam.recyclist.modifiers;

import android.util.Log;

import com.valtech.amsterdam.recyclist.RecyclistAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jasper.van.zijp on 24-7-2017.
 */

public class Updater<TModel> {
    private final static String fLogTag = "UPDATER";
    private List<Integer> mLastInsertedList;

    private List<TModel> mObjects;
    private RecyclistAdapter<TModel> mAdapter;
    private PositionDeterminator<TModel> mPositionDeterminator;
    private Inserter<TModel> mInserter;

    public Updater(List<TModel> objects, RecyclistAdapter<TModel> adapter, PositionDeterminator<TModel> positionDeterminator, Inserter<TModel> inserter) {
        mObjects = objects;
        mAdapter = adapter;
        mPositionDeterminator = positionDeterminator;
        mInserter = inserter;
        mLastInsertedList = new ArrayList<>();
    }

    public int add(TModel object) {
        Log.d(fLogTag, "add: " + object);

        int position = mPositionDeterminator.getPosition(mObjects, object);
        Log.d(fLogTag, "add at position: " + position);

        if (mInserter.insert(mObjects, object, position)) {
            mLastInsertedList.add(position);
        }

        return position;
    }

    public void notifyItemInserted() {
        Log.d(fLogTag, "notifyItemInserted count: " + mLastInsertedList.size());
        Log.d(fLogTag, "notifyItemInserted adapter item count: " + mAdapter.getItemCount());

        while(mLastInsertedList.size() > 0) {
            Log.d(fLogTag, "notifyItemInserted item:" + mLastInsertedList.size());
            try {
                mAdapter.notifyItemInserted(1);
            } catch (IndexOutOfBoundsException e) {
                Log.e("Error", "IndexOutOfBoundsException in RecyclerView happens");
            }
            mLastInsertedList.remove(0);
        }
    }
}
