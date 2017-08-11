package com.valtech.amsterdam.vris.model;

import android.content.ContentValues;
import android.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;


/**
 * Created by jasper.van.zijp on 18-7-2017.
 */

/**
 * An empty @ITimeSlot between two @Reservations
 */
public class TimeSlot extends BaseObservable implements ITimeSlot {
    @SerializedName("Id") private int mId;
    @SerializedName("Start") private LocalDateTime mStart;
    @SerializedName("End") private LocalDateTime mEnd;
    private boolean isSelected;

    /**
     * Initiate a new timeslot
     * @param id
     * @param start
     * @param end
     */
    public TimeSlot(int id, LocalDateTime start, LocalDateTime end) {
        mId = id;
        mStart = start;
        mEnd = end;
    }

    /**
     * Get the id of the current TimeSlot
     * @return
     */
    public int getId() {
        return mId;
    }

    /**
     * Get the date the TimeSlot starts in local time
     * @return
     */
    public LocalDateTime getStart() {
        return mStart;
    }

    /**
     * Get the date the TimeSlot end in local time
     * @return
     */
    public LocalDateTime getEnd() {
        return mEnd;
    }

    /**
     * Get the length of the TimeSlot in minutes
     * @return
     */
    public int getDurationInMinutes(){
        long diffInMillis =
            mEnd.toDateTime(DateTimeZone.UTC).getMillis() -
            mStart.toDateTime(DateTimeZone.UTC).getMillis();
        int diffInSeconds = (int) (diffInMillis / 1000);
        return diffInSeconds / 60;
    }

    /**
     * Get whether or not this TimeSlot is the selected TimeSlot in the menu
     * @return
     */
    public boolean getSelected(){
        return isSelected;
    }
    /**
     * Set this TimeSlot to be selected
     * @param isSelected
     */
    public void setSelected(boolean isSelected){
        this.isSelected = isSelected;
    }

    /**
     * Serialize the content
     * @return
     */
    public ContentValues toContentValues() {
        ContentValues cv = new ContentValues();
        cv.put("Id", getId());
        cv.put("Start", getStart().toString());
        cv.put("End", getStart().toString());
        return cv;
    }

    /**
     * Deserialize the content
     * @return
     */
    public static TimeSlot fromContentValues(ContentValues cv) {
        int id = cv.getAsInteger("Id");
        LocalDateTime start = DateTime.parse(cv.getAsString("Start")).toLocalDateTime();
        LocalDateTime end = DateTime.parse(cv.getAsString("End")).toLocalDateTime();

        return new TimeSlot(id, start, end);
    }
}
