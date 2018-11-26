package com.keltapps.musicalzodiacpiano.dagger.module.audio.service.data

import com.keltapps.musicalzodiacpiano.audio.service.data.repository.MakrokosmosMusicLibraryRepository
import com.keltapps.musicalzodiacpiano.audio.service.domain.repository.MusicLibraryRepository
import dagger.*
import javax.inject.Singleton

@Module
interface AudioServiceDataModule {

    @Binds
    @Singleton
    fun provideMusicLibrary(library: MakrokosmosMusicLibraryRepository): MusicLibraryRepository
}