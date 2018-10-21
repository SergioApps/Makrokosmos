package com.keltapps.makrokosmos.dagger

import android.app.Application
import com.keltapps.makrokosmos.CosmosApplication
import com.keltapps.makrokosmos.dagger.module.song.data.SongRepositoryModule
import com.keltapps.makrokosmos.dagger.module.AppModule
import com.keltapps.makrokosmos.dagger.module.info.data.InfoRepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ActivityBuilder::class,
    AppModule::class,
    SongRepositoryModule::class,
    InfoRepositoryModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: CosmosApplication)
}