package com.keltapps.makrokosmos.dagger

import android.app.Application
import com.keltapps.makrokosmos.MakrokosmosApplication
import com.keltapps.makrokosmos.audio.client.domain.repository.AudioRepository
import com.keltapps.makrokosmos.dagger.module.AppModule
import com.keltapps.makrokosmos.dagger.module.MainSingletonModule
import com.keltapps.makrokosmos.dagger.module.audio.client.AudioClientModule
import com.keltapps.makrokosmos.dagger.module.audio.service.data.AudioServiceDataModule
import com.keltapps.makrokosmos.dagger.module.info.data.InfoDataModule
import com.keltapps.makrokosmos.dagger.module.song.data.SongDataModule
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