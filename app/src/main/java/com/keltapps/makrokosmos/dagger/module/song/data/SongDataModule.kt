package com.keltapps.makrokosmos.dagger.module.song.data

import com.keltapps.makrokosmos.song.data.repository.MakrokosmosCDRepository
import com.keltapps.makrokosmos.song.domain.repository.CDRepository
import dagger.*
import javax.inject.Singleton

@Module
interface SongDataModule {

    @Binds
    @Singleton
    fun provideCDRepository(repository: MakrokosmosCDRepository): CDRepository
}