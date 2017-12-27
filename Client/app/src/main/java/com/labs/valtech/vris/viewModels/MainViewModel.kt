package com.labs.valtech.vris.viewModels

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.labs.valtech.vris.BR


/**
 * Created by marvin.brouwer on 21-12-2017.
 */
data class MainViewModel(
        private var _roomName: String = "",
        private var _valid: Boolean = false
): BaseObservable() {

    var RoomName
        @Bindable get() = _roomName
        set(value) {
            _roomName = value
            notifyPropertyChanged(BR.roomName);
        }

    var Valid: Boolean
        @Bindable get() = _valid
        set(value) {
            _valid = value
            notifyPropertyChanged(BR.valid);
        }
}