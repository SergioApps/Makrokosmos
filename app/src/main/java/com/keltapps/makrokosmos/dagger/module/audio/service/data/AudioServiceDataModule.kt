package com.keltapps.makrokosmos.dagger.module.audio.service.data

import com.keltapps.makrokosmos.audio.service.data.repository.MakrokosmosMusicLibraryRepository
import com.keltapps.makrokosmos.audio.service.domain.repository.MusicLibraryRepository
import dagger.*
import javax.inject.Singleton

@Module
interface AudioServiceDataModule {

    @Binds
    @Singleton
    fun provideMusicLibrary(library: MakrokosmosMusicLibraryRepository): MusicLibraryRepository
}