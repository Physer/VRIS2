package com.valtech.amsterdam.vris.model;

import com.google.gson.annotations.SerializedName;
import com.valtech.amsterdam.recyclist.annotation.ApiInfo;

import java.util.Date;

/**
 * Created by jasper.van.zijp on 14-7-2017.
 */
@ApiInfo(methodName = "reservation")
public class Reservation {
    @SerializedName("Id") private int mId;
    @SerializedName("Start") private Date mStart;
    @SerializedName("End") private Date mEnd;
    @SerializedName("Booker") private Person mBooker;
    @SerializedName("Room") private Room mRoom;

    public Reservation(int id, Date start, Date end, Person booker, Room room) {
        mId = id;
        mStart = start;
        mEnd = end;
        mBooker = booker;
        mRoom = room;
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

    public Person getBooker() {
        return mBooker;
    }

    public void setBooker(Person booker) {
        mBooker = booker;
    }

    public Room getRoom() {
        return mRoom;
    }

    public void setRoom(Room room) {
        mRoom = room;
    }
}
