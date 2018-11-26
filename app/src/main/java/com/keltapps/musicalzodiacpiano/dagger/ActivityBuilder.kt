package com.keltapps.musicalzodiacpiano.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.keltapps.musicalzodiacpiano.dagger.scope.ActivityScope
import com.keltapps.musicalzodiacpiano.main.view.MainActivity
import com.keltapps.musicalzodiacpiano.dagger.module.MainModule

@Module
interface ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentBuilder::class, MainModule::class])
    fun bindMainActivity(): MainActivity
}