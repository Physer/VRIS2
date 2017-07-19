package com.valtech.amsterdam.vris.model;

import com.google.gson.annotations.SerializedName;
import com.valtech.amsterdam.recyclist.annotation.ApiInfo;

import org.joda.time.DateTime;



/**
 * Created by jasper.van.zijp on 14-7-2017.
 */
@ApiInfo(methodName = "reservation")
public class Reservation extends TimeSlot {
    @SerializedName("Booker") private Person mBooker;

    public Reservation(int id, DateTime start, DateTime end, Person booker) {
        super(id, start, end);
        mBooker = booker;
    }

    public Person getBooker() {
        return mBooker;
    }

    public void setBooker(Person booker) {
        mBooker = booker;
    }
}
