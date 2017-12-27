package com.labs.valtech.vris.viewModels

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.labs.valtech.vris.BR
import com.labs.valtech.vris.models.IRoom
import org.joda.time.DateTime
import org.joda.time.LocalDateTime


/**
 * Created by marvin.brouwer on 21-12-2017.
 */
data class RoomViewModel(
        private var _room: IRoom,
        private var _available: Boolean = true,
        private var _date: LocalDateTime = DateTime.now().toLocalDateTime()
): BaseObservable() {

    var Room
        @Bindable get() = _room
        set(value) {
            _room = value
            notifyPropertyChanged(BR.roomName);
        }
    var Date
        @Bindable get() = _date
        set(value) {
            _date = value
            notifyPropertyChanged(BR.date);
        }

    var Available
        @Bindable get() = _available
        set(value) {
            _available = value
            notifyPropertyChanged(BR.available);
        }
}