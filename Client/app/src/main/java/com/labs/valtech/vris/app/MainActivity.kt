package com.labs.valtech.vris.app

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.github.salomonbrys.kodein.instance
import com.labs.valtech.vris.R
import com.labs.valtech.vris.app.base.BaseActivity
import com.labs.valtech.vris.repositories.settings.ISettingRepository
import com.labs.valtech.vris.viewModels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


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

        setModel(this, R.layout.activity_main, MainViewModel(arrayListOf("Test")))
        roomName.setAdapter(ArrayAdapter(roomName.context, android.R.layout.simple_list_item_1, Model.Options))
        roomName.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(adapter: AdapterView<*>?, view: View?, index: Int, id: Long) = submitRoom()
        }
    }

    fun submitRoom() {
        Log.d("submitRoom", "called")
        validateRoomName()
        if(!Model.Valid) return

        _settingRepository.RoomId = Model.RoomName
        navigateToAvailabilityActivity()
    }

    fun validateRoomName() {
        Model.Valid =
            !Model.RoomName.isEmpty() &&
            Model.Options.contains(Model.RoomName)
    }

    private fun navigateToAvailabilityActivity() {
        Log.i("roomId",_settingRepository.RoomId)
    }
}
