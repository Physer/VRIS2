package com.labs.valtech.vris.repositories.settings

/**
 * Created by marvin.brouwer on 19-12-2017.
 */

class SettingRepository: ISettingRepository {

    private var _roomId: String? = null;

    override var RoomId: String?
        get() = this._roomId
        set(value: String?) { this._roomId = value!! }

}