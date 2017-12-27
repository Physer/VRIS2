package com.labs.valtech.vris.viewModels

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.labs.valtech.vris.BR
import com.labs.valtech.vris.models.IRoom


/**
 * Created by marvin.brouwer on 21-12-2017.
 */
data class RoomViewModel(
        private var _room: IRoom
): BaseObservable() {

    var Room
        @Bindable get() = _room
        set(value) {
            _room = value
            notifyPropertyChanged(BR.roomName);
        }
}