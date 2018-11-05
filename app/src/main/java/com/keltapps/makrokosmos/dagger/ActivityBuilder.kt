package com.keltapps.makrokosmos.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.keltapps.makrokosmos.dagger.scope.ActivityScope
import com.keltapps.makrokosmos.main.view.MainActivity
import com.keltapps.makrokosmos.dagger.module.MainModule

@Module
interface ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentBuilder::class, MainModule::class])
    fun bindMainActivity(): MainActivity
}