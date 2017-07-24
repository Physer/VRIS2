package com.valtech.amsterdam.vris.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.SerializedName;
import com.valtech.amsterdam.vris.BR;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;


/**
 * Created by jasper.van.zijp on 18-7-2017.
 */

public class TimeSlot extends BaseObservable implements ITimeSlot {
    @SerializedName("Id") private int mId;
    @SerializedName("Start") private LocalDateTime mStart;
    @SerializedName("End") private LocalDateTime mEnd;
    private boolean isSelected;

    public TimeSlot(int id, LocalDateTime start, LocalDateTime end) {
        mId = id;
        mStart = start;
        mEnd = end;
    }

    @Bindable
    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public LocalDateTime getStartDate() {
        return mStart;
    }

    public void setStartDate(LocalDateTime start) {
        mStart = start;
        notifyPropertyChanged(BR.startDate);
    }

    @Bindable
    public LocalDateTime getEndDate() {
        return mEnd;
    }

    public void setEndDate(LocalDateTime end) {
        mEnd = end;
        notifyPropertyChanged(BR.endDate);
    }

    @Bindable
    public int getDurationInMinutes(){
        long diffInMillis =
            mEnd.toDateTime(DateTimeZone.UTC).getMillis() -
            mStart.toDateTime(DateTimeZone.UTC).getMillis();
        int diffInSeconds = (int) (diffInMillis / 1000);
        return diffInSeconds / 60;
    }

    @Bindable
    public boolean getSelected(){
        return isSelected;
    }
    public void setSelected(boolean isSelected){
        this.isSelected = isSelected;
        notifyPropertyChanged(BR.selected);
    }
}
