package com.labs.valtech.vris.repositories.settings

import com.orhanobut.hawk.Hawk

/**
 * Created by marvin.brouwer on 19-12-2017.
 */

class SettingRepository: ISettingRepository {

    private var _roomId: String? = Hawk.get("roomId", null)

    override var RoomId: String?
        get() = this._roomId
        set(value: String?) {
            this._roomId = value!!
            Hawk.put("roomId", value)
        }

}