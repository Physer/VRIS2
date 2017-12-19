package com.labs.valtech.vris

import com.labs.valtech.vris.app.MainActivity
import com.labs.valtech.vris.repositories.settings.ISettingRepository
import com.labs.valtech.vris.repositories.settings.SettingRepository
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton


/**
 * Created by marvin.brouwer on 19-12-2017.
 */
@Module abstract class VrisModule(val app: VrisApp) {

    // Business
    @Provides @Singleton fun provideSettingReposiotory() :ISettingRepository = SettingRepository()

    // App
    @ContributesAndroidInjector abstract fun contributeMainActivityInjector(): MainActivity
}