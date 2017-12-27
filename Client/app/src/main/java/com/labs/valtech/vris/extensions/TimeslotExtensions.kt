package com.labs.valtech.vris.extensions

import android.content.ContentValues
import com.labs.valtech.vris.models.ITimeslot
import com.labs.valtech.vris.models.Timeslot
import org.joda.time.DateTime


fun ITimeslot.toContentValues(): ContentValues {
    val cv = ContentValues()
    cv.put("id", this.id)
    cv.put("start", this.startDate.toString())
    cv.put("end", this.endDate!!.toString())
    return cv
}

fun fromContentValues(cv: ContentValues): Timeslot {
    val id = cv.getAsString("Id")!!
    val start = DateTime.parse(cv.getAsString("Start")).toLocalDateTime()
    val end = DateTime.parse(cv.getAsString("End")).toLocalDateTime()

    return Timeslot(id, start, end)
}