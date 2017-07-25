package com.valtech.amsterdam.vris.model;


import org.joda.time.LocalDateTime;

/**
 * Created by marvin.brouwer on 19-7-2017.
 */

public interface ITimeSlot{

    int getId();

    LocalDateTime getStartDate();

    LocalDateTime getEndDate();

    int getDurationInMinutes();

    boolean getSelected();
    void setSelected(boolean isSelected);
}
