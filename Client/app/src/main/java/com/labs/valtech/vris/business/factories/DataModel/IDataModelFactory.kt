package com.labs.valtech.vris.business.factories.DataModel

import com.google.firebase.database.DataSnapshot
import com.labs.valtech.vris.models.IRoom
import com.labs.valtech.vris.models.ITimeslot

/**
 * Created by marvin.brouwer on 27-12-2017.
 */
interface IDataModelFactory{
    fun createTimeSlot(dataSnapshot: DataSnapshot): ITimeslot
    fun createRoom(dataSnapshot: DataSnapshot): IRoom
    fun createRooms(dataSnapshot: DataSnapshot): List<IRoom>

}