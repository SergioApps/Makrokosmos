package com.keltapps.makrokosmos.makrokosmos.dagger

import com.keltapps.makrokosmos.makrokosmos.dagger.module.presentation.SongListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.keltapps.makrokosmos.makrokosmos.dagger.scope.ActivityScope
import com.keltapps.makrokosmos.presentation.songList.view.SongListActivity


@Module
interface ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = [(SongListModule::class)])
    fun bindSongListActivity(): SongListActivity
}