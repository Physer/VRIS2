package com.labs.valtech.vris

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton @Component(modules = arrayOf(VrisModule::class, AndroidInjectionModule::class))
interface VrisComponent: AndroidInjector<VrisApp> { }