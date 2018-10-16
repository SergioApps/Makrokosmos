package com.keltapps.makrokosmos.dagger.module.song.presentation

import com.keltapps.makrokosmos.dagger.scope.SubFragmentScope
import com.keltapps.makrokosmos.song.presentation.view.SongListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SongListFragmentBuilder {

    @SubFragmentScope
    @ContributesAndroidInjector(modules = [SongListFragmentModule::class])
    abstract fun bindSongListFragment(): SongListFragment
}