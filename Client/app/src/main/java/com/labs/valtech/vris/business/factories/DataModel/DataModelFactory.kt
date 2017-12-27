package com.labs.valtech.vris.business.factories.DataModel

import com.google.firebase.database.DataSnapshot
import com.labs.valtech.vris.models.IRoom
import com.labs.valtech.vris.models.ITimeslot
import com.labs.valtech.vris.models.Room
import com.labs.valtech.vris.models.Timeslot
import org.joda.time.DateTime

/**
 * Created by marvin.brouwer on 27-12-2017.
 */
class DataModelFactory() : IDataModelFactory{

    override fun createRooms(dataSnapshot: DataSnapshot): List<IRoom> =
        dataSnapshot.children.map { child -> this.createRoom(child) }

    override fun createRoom(dataSnapshot: DataSnapshot): IRoom {
        val room = dataSnapshot.getValue<Room>(Room::class.java)!!
        room.id = dataSnapshot.key;
        return room;
    }

    override fun createTimeSlot(dataSnapshot: DataSnapshot): ITimeslot {

        val startDate = DateTime.parse(dataSnapshot.child("startDate").value.toString()).toLocalDateTime()
        val endDateValue = dataSnapshot.child("endDate").value.toString();
        val endDate = if (endDateValue.isNullOrBlank()) null else DateTime.parse(endDateValue).toLocalDateTime()
        val timeslot = Timeslot(dataSnapshot.key, startDate, endDate)

        return timeslot

    }

}