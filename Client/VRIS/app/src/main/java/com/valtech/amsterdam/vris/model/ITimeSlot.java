package com.valtech.amsterdam.vris.model;


import android.databinding.Bindable;
import android.databinding.Observable;

import org.joda.time.LocalDateTime;

/**
 * Created by marvin.brouwer on 19-7-2017.
 */

public interface ITimeSlot extends Observable {

    @Bindable
    int getId();

    @Bindable
    LocalDateTime getStartDate();

    @Bindable
    LocalDateTime getEndDate();

    @Bindable
    int getDurationInMinutes();

    @Bindable
    boolean getSelected();
    void setSelected(boolean isSelected);
}
