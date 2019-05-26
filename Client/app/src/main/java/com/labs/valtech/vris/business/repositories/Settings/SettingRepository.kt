package com.labs.valtech.vris.business.repositories.Settings

import com.labs.valtech.vris.models.IRoom
import com.orhanobut.hawk.Hawk

/**
 * Created by marvin.brouwer on 19-12-2017.
 */

class SettingRepository: ISettingRepository {

    private var _room: IRoom? = Hawk.get("room", null)

    override var Room: IRoom?
        get() = this._room
        set(value: IRoom?) {
            this._room = value
            Hawk.put("room", value)
        }

}