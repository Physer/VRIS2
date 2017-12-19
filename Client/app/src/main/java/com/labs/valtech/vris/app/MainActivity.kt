package com.labs.valtech.vris.app

import android.os.Bundle
import android.util.Log
import com.labs.valtech.vris.app.base.BaseActivity
import com.labs.valtech.vris.repositories.settings.ISettingRepository
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class MainActivity : BaseActivity() {


    @Inject lateinit var _settingRepository: ISettingRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this);

        Log.i((_settingRepository == null).toString(), _settingRepository!!.RoomId)
    }
}
