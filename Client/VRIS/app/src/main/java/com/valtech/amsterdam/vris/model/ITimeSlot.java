package com.valtech.amsterdam.vris.model;


import org.joda.time.DateTime;

/**
 * Created by marvin.brouwer on 19-7-2017.
 */

public interface ITimeSlot {

    int getId();

    DateTime getStartDate();

    DateTime getEndDate();

}
