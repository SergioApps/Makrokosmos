package com.keltapps.makrokosmos.makrokosmos.dagger.module

import android.app.Application
import android.content.Context
import com.keltapps.makrokosmos.data.resourceProvider.MakrokosmosResourceProvider
import com.keltapps.makrokosmos.data.resourceProvider.ResourceProvider
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface AppModule {

    @Binds
    @Singleton
    fun provideResourceProvider(resourceProvider: MakrokosmosResourceProvider): ResourceProvider

    @Binds
    @Singleton
    fun provideApplicationContext(application: Application): Context
}