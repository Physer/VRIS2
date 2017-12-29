package com.labs.valtech.vris

/**
 * Created by marvin.brouwer on 21-12-2017.
 */
import android.content.res.Resources
import com.external.kioskmode.KioskApplication
import com.github.salomonbrys.kodein.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.labs.valtech.vris.business.factories.DataModel.DataModelFactory
import com.labs.valtech.vris.business.factories.DataModel.IDataModelFactory
import com.labs.valtech.vris.business.repositories.Settings.ISettingRepository
import com.labs.valtech.vris.business.repositories.Settings.SettingRepository
import com.orhanobut.hawk.Hawk


class VrisApplication : KioskApplication(), KodeinAware {

    override fun onCreate() {
        super.onCreate()
        Hawk.init(this.applicationContext).build();
    }

    override val kodein by Kodein.lazy {

        bind<ISettingRepository>() with singleton { SettingRepository() }
        bind<IDataModelFactory>() with singleton { DataModelFactory() }
        bind<FirebaseDatabase>() with singleton { FirebaseDatabase.getInstance() }
        bind<DatabaseReference>() with factory {
            reference: String -> GetFireBaseReference(reference)
        }
    }

    private fun GetFireBaseReference(reference: String) : DatabaseReference {
        val firebaseDb: FirebaseDatabase = kodein.instance()
        return firebaseDb.getReference(reference)
    }

    companion object {

        fun getResources(): Resources {
            return KioskApplication.ActivityContext.resources!!
        }

    }
}