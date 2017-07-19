package com.valtech.amsterdam.vris.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by jasper.van.zijp on 18-7-2017.
 */

public class TimeSlot implements ITimeSlot {
    @SerializedName("Id") private int mId;
    @SerializedName("Start") private Date mStart;
    @SerializedName("End") private Date mEnd;

    public TimeSlot(int id, Date start, Date end) {
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

    public Date getStart() {
        return mStart;
    }

    public void setStart(Date start) {
        mStart = start;
    }

    public Date getEnd() {
        return mEnd;
    }

    public void setEnd(Date end) {
        mEnd = end;
    }
}
