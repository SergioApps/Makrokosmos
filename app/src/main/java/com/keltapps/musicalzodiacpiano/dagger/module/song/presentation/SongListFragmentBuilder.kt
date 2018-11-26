package com.keltapps.musicalzodiacpiano.dagger.module.song.presentation

import com.keltapps.musicalzodiacpiano.dagger.scope.SubFragmentScope
import com.keltapps.musicalzodiacpiano.song.presentation.list.view.SongListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SongListFragmentBuilder {

    @SubFragmentScope
    @ContributesAndroidInjector(modules = [SongListFragmentModule::class])
    abstract fun bindSongListFragment(): SongListFragment
}