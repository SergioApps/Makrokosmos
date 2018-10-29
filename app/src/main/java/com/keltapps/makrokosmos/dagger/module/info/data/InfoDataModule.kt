package com.keltapps.makrokosmos.dagger.module.info.data

import com.keltapps.makrokosmos.info.data.repository.MakrokosmosInfoRepository
import com.keltapps.makrokosmos.info.domain.repository.InfoRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface InfoDataModule {
    @Singleton
    @Binds
    fun provideInfoRepository(repository: MakrokosmosInfoRepository): InfoRepository
}