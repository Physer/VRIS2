package com.valtech.amsterdam.vris.model;

import java.util.Date;

/**
 * Created by jasper.van.zijp on 18-7-2017.
 */

public class Vacancy extends TimeSlot {
    public Vacancy(int id, Date start, Date end) {
        super(id, start, end);
    }
}
