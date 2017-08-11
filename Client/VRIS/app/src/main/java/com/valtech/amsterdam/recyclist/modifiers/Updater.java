package com.valtech.amsterdam.recyclist.modifiers;

import android.util.Log;

import com.valtech.amsterdam.recyclist.RecyclistAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jasper.van.zijp on 24-7-2017.
 */

public class Updater<TModel extends IHasId> {
    private final static String fLogTag = "UPDATER";
    private List<Integer> mLastInsertedList;
    private List<Integer> mLastUpdatedList;

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
        mLastUpdatedList = new ArrayList<>();
    }

    public List<TModel> getList() { return mObjects; }

    public int add(TModel object) {
        Log.d(fLogTag, "add: " + object);

        int position = mPositionDeterminator.getPosition(mObjects, object);
        Log.d(fLogTag, "add at position: " + position);

        mLastUpdatedList.add(position);

        return position;
    }

    public int update(TModel object) {
        Log.d(fLogTag, "update: " + object);

        int position = mObjects.indexOf(object);
        Log.d(fLogTag, "update at position: " + position);

        mLastUpdatedList.add(position);

        return position;
    }

    public void notifyItemInserted() {
        Log.d(fLogTag, "notifyItemInserted count: " + mLastInsertedList.size());
        Log.d(fLogTag, "notifyItemInserted adapter item count: " + mAdapter.getItemCount());

        while(mLastInsertedList.size() > 0) {
            Log.d(fLogTag, "notifyItemInserted item:" + mLastInsertedList.size());
            try {
                mAdapter.notifyItemInserted(mLastInsertedList.get(0));
            } catch (IndexOutOfBoundsException e) {
                Log.e("Error", "IndexOutOfBoundsException in RecyclerView happens");
            }
            mLastInsertedList.remove(0);
        }

        Log.d(fLogTag, "notifyItemInserted adapter item count after: " + mAdapter.getItemCount());
    }

    public void notifyItemUpdated() {
        Log.d(fLogTag, "notifyItemUpdated count: " + mLastUpdatedList.size());
        Log.d(fLogTag, "notifyItemUpdated adapter item count: " + mAdapter.getItemCount());

        while(mLastUpdatedList.size() > 0) {
            Log.d(fLogTag, "notifyItemUpdated item:" + mLastUpdatedList.size());
            try {
                mAdapter.notifyItemChanged(mLastUpdatedList.get(0));
            } catch (IndexOutOfBoundsException e) {
                Log.e("Error", "IndexOutOfBoundsException in RecyclerView happens");
            }
            mLastUpdatedList.remove(0);
        }

        Log.d(fLogTag, "notifyItemUpdated adapter item count after: " + mAdapter.getItemCount());
    }

    public TModel getById(int id) {
        if(id < 0) throw new IndexOutOfBoundsException();

        for (TModel timeSlot: mObjects) {
            if(id != timeSlot.getId()) continue;
            return timeSlot;
        }

        throw new IndexOutOfBoundsException();

    }
}
