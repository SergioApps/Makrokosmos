package com.keltapps.makrokosmos.dagger.module.song.data

import com.keltapps.makrokosmos.song.data.repository.MakrokosmosCDRepository
import com.keltapps.makrokosmos.song.domain.repository.CDRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface SongRepositoryModule {

    @Singleton
    @Binds
    fun provideCDRepository(repository: MakrokosmosCDRepository): CDRepository
}