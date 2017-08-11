package com.valtech.amsterdam.vris.model;


import com.valtech.amsterdam.recyclist.modifiers.IHasId;

import org.joda.time.LocalDateTime;

/**
 * Created by marvin.brouwer on 19-7-2017.
 */

public interface ITimeSlot extends IHasId {

    int getId();

    LocalDateTime getStart();

    LocalDateTime getEnd();

    int getDurationInMinutes();

    boolean getSelected();
    void setSelected(boolean isSelected);
}
