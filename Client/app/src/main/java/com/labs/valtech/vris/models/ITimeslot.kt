package com.labs.valtech.vris.models


import org.joda.time.LocalDateTime

/**
 * Created by marvin.brouwer on 19-7-2017.
 */

interface ITimeslot {

    val id: Int

    val startDate: LocalDateTime

    val endDate: LocalDateTime?

    val durationInMinutes: Int
}
