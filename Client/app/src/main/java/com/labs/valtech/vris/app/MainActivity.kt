package com.labs.valtech.vris.app

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.github.salomonbrys.kodein.instance
import com.labs.valtech.vris.BR
import com.labs.valtech.vris.R
import com.labs.valtech.vris.app.base.BaseActivity
import com.labs.valtech.vris.databinding.ActivityMainBinding
import com.labs.valtech.vris.repositories.settings.ISettingRepository
import com.labs.valtech.vris.viewModels.MainViewModel


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class MainActivity : BaseActivity() {

    val _settingRepository: ISettingRepository by instance()

    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(_settingRepository.RoomId != null){
            // navigate to roomactivity
        }

        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        _binding.setVariable(BR.model, MainViewModel())

        _binding.executePendingBindings()
    }
}
