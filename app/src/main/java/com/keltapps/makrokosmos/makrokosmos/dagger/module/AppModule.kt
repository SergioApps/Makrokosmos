package com.keltapps.makrokosmos.makrokosmos.dagger.module

import com.keltapps.makrokosmos.data.resourceProvider.MakrokosmosResourceProvider
import com.keltapps.makrokosmos.data.resourceProvider.ResourceProvider
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideResourceProvider(resourceProvider: MakrokosmosResourceProvider): ResourceProvider
}