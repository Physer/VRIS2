package com.labs.valtech.vris

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject



/**
 * Created by marvin.brouwer on 19-12-2017.
 */
class VrisApp : Application(), HasActivityInjector {

    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    override fun activityInjector(): AndroidInjector<Activity>? = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        DaggerVrisComponent
            .builder()
            .build()
    }

}

