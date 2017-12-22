package com.labs.valtech.vris.viewModels

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.labs.valtech.vris.BR



/**
 * Created by marvin.brouwer on 21-12-2017.
 */
data class RoomViewModel(
        private var _roomName: String = ""
): BaseObservable() {

    var RoomName
        @Bindable get() = _roomName
        set(value) {
            _roomName = value
            notifyPropertyChanged(BR.roomName);
        }
}