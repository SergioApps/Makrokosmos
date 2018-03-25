package com.keltapps.makrokosmos.makrokosmos.dagger

import android.app.Application
import com.keltapps.makrokosmos.makrokosmos.CosmosApplication
import com.keltapps.makrokosmos.makrokosmos.dagger.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ActivityBuilder::class,
    AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: CosmosApplication)
}