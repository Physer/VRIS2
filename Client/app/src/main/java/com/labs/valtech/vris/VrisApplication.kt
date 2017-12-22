package com.labs.valtech.vris

/**
 * Created by marvin.brouwer on 21-12-2017.
 */
import android.app.Application
import com.github.salomonbrys.kodein.*
import com.labs.valtech.vris.repositories.settings.ISettingRepository
import com.labs.valtech.vris.repositories.settings.SettingRepository
import com.orhanobut.hawk.Hawk

class VrisApplication : Application(), KodeinAware {

    override fun onCreate() {
        super.onCreate()
        Hawk.init(this.applicationContext).build();
    }

    override val kodein by Kodein.lazy {

        bind<ISettingRepository>() with instance(SettingRepository())
    }
}