package com.labs.valtech.vris.models

import com.google.gson.annotations.SerializedName

import org.joda.time.DateTimeZone
import org.joda.time.LocalDateTime


/**
 * Created by jasper.van.zijp on 18-7-2017.
 */

class Timeslot(
    @field:SerializedName("id") override var id: String,
    @field:SerializedName("start") override var startDate: LocalDateTime,
    @field:SerializedName("end") override var endDate: LocalDateTime?) : ITimeslot {

    override val durationInMinutes: Int
        get() {
            val diffInMillis = endDate!!.toDateTime(DateTimeZone.UTC).millis - startDate.toDateTime(DateTimeZone.UTC).millis
            val diffInSeconds = (diffInMillis / 1000).toInt()
            return diffInSeconds / 60
        }
}