package com.keltapps.musicalzodiacpiano.dagger.module

import android.app.Application
import android.content.Context
import com.keltapps.musicalzodiacpiano.base.resourceprovider.MakrokosmosResourceProvider
import com.keltapps.musicalzodiacpiano.base.resourceprovider.ResourceProvider
import dagger.Binds
import dagger.Module
import javax.inject.Named
import javax.inject.Singleton

@Module
interface AppModule {

    @Binds
    @Singleton
    fun provideResourceProvider(resourceProvider: MakrokosmosResourceProvider): ResourceProvider

    @Binds
    @Singleton
    @Named("applicationContext")
    fun provideApplicationContext(application: Application): Context
}