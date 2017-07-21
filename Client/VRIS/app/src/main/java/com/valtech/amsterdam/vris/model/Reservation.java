package com.valtech.amsterdam.vris.model;

import com.google.gson.annotations.SerializedName;
import com.valtech.amsterdam.recyclist.annotation.ApiInfo;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;


/**
 * Created by jasper.van.zijp on 14-7-2017.
 */
@ApiInfo(methodName = "reservation")
public class Reservation extends TimeSlot {
    @SerializedName("Booker") private Person mBooker;
    @SerializedName("Title") private String title;

    public Reservation(int id, String title, LocalDateTime start, LocalDateTime end, Person booker) {
        super(id, start, end);
        mBooker = booker;
        this.title = title;
    }

    public Person getBooker() {
        return mBooker;
    }

    public void setBooker(Person booker) {
        mBooker = booker;
    }

    public String getTitle() {
        return title;
    }
}
