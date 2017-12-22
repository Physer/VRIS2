package com.labs.valtech.vris.app

import android.os.Bundle
import android.util.Log
import com.github.salomonbrys.kodein.instance
import com.labs.valtech.vris.R
import com.labs.valtech.vris.app.base.BaseActivity
import com.labs.valtech.vris.repositories.settings.ISettingRepository
import com.labs.valtech.vris.viewModels.MainViewModel


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class MainActivity : BaseActivity<MainViewModel>() {

    val _settingRepository: ISettingRepository by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(_settingRepository.RoomId != null)
            navigateToAvailabilityActivity()

       setModel(R.layout.activity_main, MainViewModel())
    }

    fun submitRoom() {
        _settingRepository.RoomId = Model.RoomName
        navigateToAvailabilityActivity()
    }

    fun validateRoomName() {
        Model.Valid = !Model.RoomName.isEmpty()
    }

    private fun navigateToAvailabilityActivity() {
        Log.i("roomId",_settingRepository.RoomId)
    }
}
