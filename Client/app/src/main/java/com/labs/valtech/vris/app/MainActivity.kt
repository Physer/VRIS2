package com.labs.valtech.vris.app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.github.salomonbrys.kodein.instance
import com.labs.valtech.vris.R
import com.labs.valtech.vris.adapters.RoomAutoCompleteAdapter
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

    private lateinit var _roomAutoCompleteAdapter: RoomAutoCompleteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setModel(this, R.layout.activity_main, MainViewModel())

        _roomAutoCompleteAdapter = RoomAutoCompleteAdapter(this, kodein)
        roomName.setAdapter(_roomAutoCompleteAdapter)
        roomName.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(adapter: AdapterView<*>?, view: View?, index: Int, id: Long) = {
                val room = _roomAutoCompleteAdapter.getItem(index);
                Model.RoomName = room.getFullRoomName()
                _settingRepository.Room = room
                navigateToAvailabilityActivity()
            }()
        }
    }

    override fun onStart() {
        if(_settingRepository.Room != null)
            navigateToAvailabilityActivity()
        super.onStart()
        roomName.requestFocus()
    }

    fun submitRoom() {
        validateRoomName()
        if(!Model.Valid) return

        val room = _roomAutoCompleteAdapter.findRooms(Model.RoomName).first()
        _settingRepository.Room = room

        navigateToAvailabilityActivity()
    }

    fun validateRoomName() {
        Model.Valid =
            !Model.RoomName.isEmpty() &&
            _roomAutoCompleteAdapter.findRooms(Model.RoomName)
                    .any{ room -> room.getFullRoomName().equals(Model.RoomName, true) }
    }

    private fun navigateToAvailabilityActivity() {
        Log.i("roomId",_settingRepository.Room!!.id)
        startActivity(Intent(this, RoomActivity::class.java))
    }
}
