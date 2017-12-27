package com.labs.valtech.vris

/**
 * Created by marvin.brouwer on 21-12-2017.
 */
import android.app.Activity
import android.app.Application
import android.content.Intent
import com.external.kioskmode.HomeWatcher
import com.external.kioskmode.IHomePressedListener
import com.github.salomonbrys.kodein.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.labs.valtech.vris.business.repositories.Settings.ISettingRepository
import com.labs.valtech.vris.business.repositories.Settings.SettingRepository
import com.orhanobut.hawk.Hawk


class VrisApplication : Application(), KodeinAware {

    private var _homeWatcher = HomeWatcher(this)
    lateinit var ActivityContext: Activity

    override fun onCreate() {
        super.onCreate()
        Hawk.init(this.applicationContext).build();
        _homeWatcher.setOnHomePressedListener(object : IHomePressedListener {
            override fun onHomePressed() = reset()
            override fun onRecentAppsPressed() = reset()

            private fun reset(){
                if(ActivityContext == null) return
                ActivityContext.finish()
                startActivity(Intent(ActivityContext, ActivityContext::class.java))
            }
        })
        _homeWatcher.startWatch()
    }

    override fun onTerminate() {
        _homeWatcher.stopWatch()
        super.onTerminate()
    }

    override val kodein by Kodein.lazy {

        bind<ISettingRepository>() with instance(SettingRepository())
        bind<FirebaseDatabase>() with singleton { FirebaseDatabase.getInstance() }
        bind<DatabaseReference>() with factory {
            reference: String -> GetFireBaseReference(reference)
        }
    }

    private fun GetFireBaseReference(reference: String) : DatabaseReference {
        val firebaseDb: FirebaseDatabase = kodein.instance()
        return firebaseDb.getReference(reference)
    }

}