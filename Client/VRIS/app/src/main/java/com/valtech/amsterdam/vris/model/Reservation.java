package com.valtech.amsterdam.vris.model;

import android.databinding.Bindable;

import com.google.gson.annotations.SerializedName;
import com.valtech.amsterdam.recyclist.annotation.ApiInfo;
import com.valtech.amsterdam.vris.BR;

import org.joda.time.LocalDateTime;

import java.util.List;

import javax.annotation.Nullable;


/**
 * Created by jasper.van.zijp on 14-7-2017.
 */

/**
 * A reservation made in the Office calendar
 */
@ApiInfo(methodName = "reservation")
public final class Reservation extends TimeSlot {
    @SerializedName("Title") protected String mTitle;
    @SerializedName("Organizer") protected Person mOrganizer;
    @SerializedName("Attendees") @Nullable protected List<Person> mAttendees;

    /**
     * Initiate a new reservation
     * @param id
     * @param title
     * @param start
     * @param end
     * @param organizer
     * @param attendees
     */
    public Reservation(int id, String title, LocalDateTime start, LocalDateTime end, Person organizer, @Nullable List<Person> attendees) {
        super(id, start, end);
        mTitle = title;
        mOrganizer = organizer;
        mAttendees = attendees;
    }

    /**
     * Get the @Person who organizes this Reservation
     * @return
     */
    public Person getOrganizer() {
        return mOrganizer;
    }

    /**
     * Get the possible attendees
     * @return
     */
    @Nullable
    public List<Person> getAttendees() {
        return mAttendees;
    }

    /**
     * Get the title of the reservation
     * @return
     */
    @Bindable
    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String value) {
        mTitle = value;
        notifyPropertyChanged(BR.title);
    }

    @Override
    public LocalDateTime getStart() {
        return super.getStart();
    }
    @Override
    public LocalDateTime getEnd() {
        return super.getEnd();
    }
}
