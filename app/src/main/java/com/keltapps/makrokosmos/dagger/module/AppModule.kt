package com.keltapps.makrokosmos.dagger.module

import android.app.Application
import android.content.Context
import com.keltapps.makrokosmos.song.data.resourceprovider.MakrokosmosResourceProvider
import com.keltapps.makrokosmos.song.data.resourceprovider.ResourceProvider
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