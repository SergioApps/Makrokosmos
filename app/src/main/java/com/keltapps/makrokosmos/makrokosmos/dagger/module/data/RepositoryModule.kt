package com.keltapps.makrokosmos.makrokosmos.dagger.module.data

import com.keltapps.makrokosmos.data.repository.MakrokosmosCDRepository
import com.keltapps.makrokosmos.domain.repository.CDRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun provideCDRepository(repository: MakrokosmosCDRepository): CDRepository
}