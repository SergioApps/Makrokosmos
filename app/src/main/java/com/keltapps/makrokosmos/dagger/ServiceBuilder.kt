package com.keltapps.makrokosmos.dagger

import com.keltapps.makrokosmos.audio.service.MusicService
import com.keltapps.makrokosmos.dagger.module.MusicServiceModule
import com.keltapps.makrokosmos.dagger.scope.ServiceScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ServiceBuilder {

    @ServiceScope
    @ContributesAndroidInjector(modules = [MusicServiceModule::class])
    fun bindMusicService(): MusicService
}