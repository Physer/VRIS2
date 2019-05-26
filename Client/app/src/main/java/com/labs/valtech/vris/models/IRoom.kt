package com.labs.valtech.vris.models

/**
 * Created by marvin.brouwer on 19-12-2017.
 */
interface IRoom {

    var id: String
    var country: String
    var venue: String
    var name: String

    fun getFullRoomName(): String
}