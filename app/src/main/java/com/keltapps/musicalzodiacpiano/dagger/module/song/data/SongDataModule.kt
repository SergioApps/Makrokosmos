package com.keltapps.musicalzodiacpiano.dagger.module.song.data

import com.keltapps.musicalzodiacpiano.song.data.repository.MakrokosmosCDRepository
import com.keltapps.musicalzodiacpiano.song.domain.repository.CDRepository
import dagger.*
import javax.inject.Singleton

@Module
interface SongDataModule {

    @Binds
    @Singleton
    fun provideCDRepository(repository: MakrokosmosCDRepository): CDRepository
}