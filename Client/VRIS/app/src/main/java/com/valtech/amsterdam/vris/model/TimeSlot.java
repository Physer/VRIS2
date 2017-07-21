package com.valtech.amsterdam.vris.model;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;


/**
 * Created by jasper.van.zijp on 18-7-2017.
 */

public class TimeSlot implements ITimeSlot {
    @SerializedName("Id") private int mId;
    @SerializedName("Start") private LocalDateTime mStart;
    @SerializedName("End") private LocalDateTime mEnd;
    private boolean isSelected;

    public TimeSlot(int id, LocalDateTime start, LocalDateTime end) {
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

    public LocalDateTime getStartDate() {
        return mStart;
    }

    public void setStartDate(LocalDateTime start) {
        mStart = start;
    }

    public LocalDateTime getEndDate() {
        return mEnd;
    }

    public void setEndDate(LocalDateTime end) {
        mEnd = end;
    }

    public int getDurationInMinutes(){
        long diffInMillis =
            mEnd.toDateTime(DateTimeZone.UTC).getMillis() -
            mStart.toDateTime(DateTimeZone.UTC).getMillis();
        int diffInSeconds = (int) (diffInMillis / 1000);
        return diffInSeconds / 60;
    }

    public boolean getSelected(){
        return isSelected;
    }
    public void setSelected(boolean isSelected){
        this.isSelected = isSelected;
    }
}
