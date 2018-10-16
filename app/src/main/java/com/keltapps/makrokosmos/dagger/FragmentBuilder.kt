package com.keltapps.makrokosmos.dagger

import com.keltapps.makrokosmos.dagger.module.menu.presentation.MenuModule
import com.keltapps.makrokosmos.dagger.module.song.presentation.SongListFragmentBuilder
import com.keltapps.makrokosmos.dagger.module.song.presentation.SongListParentModule
import com.keltapps.makrokosmos.dagger.scope.FragmentScope
import com.keltapps.makrokosmos.menu.presentation.view.MenuFragment
import com.keltapps.makrokosmos.song.presentation.view.SongListParentFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBuilder {

    @FragmentScope
    @ContributesAndroidInjector(modules = [MenuModule::class])
    fun bindMenuFragment(): MenuFragment


    @FragmentScope
    @ContributesAndroidInjector(modules = [SongListParentModule::class, SongListFragmentBuilder::class])
    fun bindSongListParentFragment(): SongListParentFragment
}