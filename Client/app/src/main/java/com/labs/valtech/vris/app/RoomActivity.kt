package com.labs.valtech.vris.app

import android.content.Intent
import android.os.Bundle
import com.github.salomonbrys.kodein.instance
import com.labs.valtech.vris.R
import com.labs.valtech.vris.app.base.BaseActivity
import com.labs.valtech.vris.repositories.settings.ISettingRepository
import com.labs.valtech.vris.viewModels.RoomViewModel
import kotlinx.android.synthetic.main.activity_room.*


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class RoomActivity : BaseActivity<RoomViewModel>() {

    val _settingRepository: ISettingRepository by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setModel(this, R.layout.activity_room, RoomViewModel(_settingRepository.Room!!))
    }

    override fun onStart() {
        if(_settingRepository.Room == null)
            navigateToMainActivity()
        super.onStart()
        // todo temp
        roomName.setOnClickListener {
            _settingRepository.Room = null
            navigateToMainActivity()
        }
    }

    private fun navigateToMainActivity() = startActivity(Intent(this, MainActivity::class.java))
}
