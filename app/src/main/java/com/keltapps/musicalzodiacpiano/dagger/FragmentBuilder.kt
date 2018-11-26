package com.keltapps.musicalzodiacpiano.dagger

import com.keltapps.musicalzodiacpiano.dagger.module.info.presentation.InfoModule
import com.keltapps.musicalzodiacpiano.dagger.module.menu.presentation.MenuModule
import com.keltapps.musicalzodiacpiano.dagger.module.song.presentation.*
import com.keltapps.musicalzodiacpiano.dagger.scope.FragmentScope
import com.keltapps.musicalzodiacpiano.info.presentation.view.InfoFragment
import com.keltapps.musicalzodiacpiano.menu.presentation.view.MenuFragment
import com.keltapps.musicalzodiacpiano.song.presentation.detail.view.SongDetailFragment
import com.keltapps.musicalzodiacpiano.song.presentation.list.view.SongListParentFragment
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

    @FragmentScope
    @ContributesAndroidInjector(modules = [SongDetailModule::class])
    fun bindSongDetailragment(): SongDetailFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [InfoModule::class])
    fun bindInfoFragment(): InfoFragment
}