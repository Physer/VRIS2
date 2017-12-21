package com.labs.valtech.vris.app

import android.os.Bundle
import com.github.salomonbrys.kodein.instance
import com.labs.valtech.vris.app.base.BaseActivity
import com.labs.valtech.vris.repositories.settings.ISettingRepository

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class MainActivity : BaseActivity() {

    val _settingRepository: ISettingRepository by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        
    }
}
