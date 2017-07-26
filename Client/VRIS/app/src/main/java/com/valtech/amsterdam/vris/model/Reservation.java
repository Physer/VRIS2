package com.valtech.amsterdam.vris.model;

import com.google.gson.annotations.SerializedName;
import com.valtech.amsterdam.recyclist.annotation.ApiInfo;

import org.joda.time.LocalDateTime;

import java.util.ArrayList;

import javax.annotation.Nullable;


/**
 * Created by jasper.van.zijp on 14-7-2017.
 */
@ApiInfo(methodName = "reservation")
public class Reservation extends TimeSlot {
    @SerializedName("Organizer") private Person mOrganizer;
    @SerializedName("Attendees") @Nullable private ArrayList<Person> mAttendees;
    @SerializedName("Title") private String mTitle;

    public Reservation(int id, String title, LocalDateTime start, LocalDateTime end, Person organizer, @Nullable ArrayList<Person> attendees) {
        super(id, start, end);
        mTitle = title;
        mOrganizer = organizer;
        mAttendees = attendees;
    }

    public Person getOrganizer() {
        return mOrganizer;
    }

    public ArrayList<Person> getAttendees() {
        return mAttendees;
    }

    public String getmTitle() {
        return mTitle;
    }
}
