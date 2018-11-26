package com.keltapps.musicalzodiacpiano.dagger.module.info.data

import com.keltapps.musicalzodiacpiano.info.data.repository.MakrokosmosInfoRepository
import com.keltapps.musicalzodiacpiano.info.domain.repository.InfoRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface InfoDataModule {
    @Singleton
    @Binds
    fun provideInfoRepository(repository: MakrokosmosInfoRepository): InfoRepository
}