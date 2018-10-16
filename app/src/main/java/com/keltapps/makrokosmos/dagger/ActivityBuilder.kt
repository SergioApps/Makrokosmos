package com.keltapps.makrokosmos.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.keltapps.makrokosmos.dagger.scope.ActivityScope
import com.keltapps.makrokosmos.main.presentation.view.MainActivity

@Module
interface ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentBuilder::class])
    fun bindMainActivity(): MainActivity
}