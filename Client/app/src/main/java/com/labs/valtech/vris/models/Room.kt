package com.labs.valtech.vris.models

import com.google.gson.annotations.SerializedName


/**
 * Created by jasper.van.zijp on 18-7-2017.
 */

class Room (
    @field:SerializedName("id") override var id: String = "",
    @field:SerializedName("venue") override var venue: String = "",
    @field:SerializedName("country") override var country: String = "",
    @field:SerializedName("name") override var name: String = "") : IRoom {

    override fun getFullRoomName(): String {
        return "$country $venue $name"
    }
}