package com.valtech.amsterdam.vris.sync;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.valtech.amsterdam.vris.CustomApplication;
import com.valtech.amsterdam.vris.model.TimeSlot;

/**
 * Created by jasper.van.zijp on 20-7-2017.
 */

public class StubProvider extends ContentProvider {
    private final static String fLogTag = "STUBPROVIDER";

    @Override
    public boolean onCreate() {
        Log.d(fLogTag, "onCreate");
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        Log.d(fLogTag, "getType");
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.d(fLogTag, "insert");

        TimeSlot ts = TimeSlot.fromContentValues(values);
        if ((getContext()) != null && ((CustomApplication)getContext()).getUpdater() != null) {
            return ContentUris.withAppendedId(uri, ((CustomApplication)getContext()).getUpdater().add(ts));
        }

        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(fLogTag, "delete");
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(fLogTag, "update");
        return 0;
    }
}
