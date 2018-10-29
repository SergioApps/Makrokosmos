package com.keltapps.makrokosmos.dagger

import android.app.Application
import com.keltapps.makrokosmos.MakrokosmosApplication
import com.keltapps.makrokosmos.dagger.module.AppModule
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
    AudioServiceDataModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: MakrokosmosApplication)
}