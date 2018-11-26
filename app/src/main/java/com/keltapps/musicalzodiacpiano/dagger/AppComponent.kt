package com.keltapps.musicalzodiacpiano.dagger

import android.app.Application
import com.keltapps.musicalzodiacpiano.MakrokosmosApplication
import com.keltapps.musicalzodiacpiano.audio.client.domain.repository.AudioRepository
import com.keltapps.musicalzodiacpiano.dagger.module.AppModule
import com.keltapps.musicalzodiacpiano.dagger.module.MainSingletonModule
import com.keltapps.musicalzodiacpiano.dagger.module.audio.client.AudioClientModule
import com.keltapps.musicalzodiacpiano.dagger.module.audio.service.data.AudioServiceDataModule
import com.keltapps.musicalzodiacpiano.dagger.module.info.data.InfoDataModule
import com.keltapps.musicalzodiacpiano.dagger.module.song.data.SongDataModule
import dagger.*
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ActivityBuilder::class,
    ServiceBuilder::class,
    AppModule::class,
    SongDataModule::class,
    InfoDataModule::class,
    AudioServiceDataModule::class,
    AudioClientModule::class,
    MainSingletonModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: MakrokosmosApplication)

    fun provideAudioRepository(): AudioRepository
}