package com.valtech.amsterdam.vris.model;

import android.content.ContentValues;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;


/**
 * Created by jasper.van.zijp on 18-7-2017.
 */

public class TimeSlot implements ITimeSlot {
    @SerializedName("Id") private int mId;
    @SerializedName("Start") private DateTime mStart;
    @SerializedName("End") private DateTime mEnd;

    public TimeSlot(int id, DateTime start, DateTime end) {
        mId = id;
        mStart = start;
        mEnd = end;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public DateTime getStartDate() {
        return mStart;
    }

    public void setStartDate(DateTime start) {
        mStart = start;
    }

    public DateTime getEndDate() {
        return mEnd;
    }

    public void setEndDate(DateTime end) {
        mEnd = end;
    }

    public ContentValues toContentValues() {
        ContentValues cv = new ContentValues();
        cv.put("Id", getId());
        cv.put("Start", getStartDate().toString());
        cv.put("End", getEndDate().toString());
        return cv;
    }

    public static TimeSlot fromContentValues(ContentValues cv) {
        int id = cv.getAsInteger("Id");
        DateTime start = DateTime.parse(cv.getAsString("Start"));
        DateTime end = DateTime.parse(cv.getAsString("End"));

        return new TimeSlot(id, start, end);
    }
}
