package com.keltapps.makrokosmos.makrokosmos

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject
import com.keltapps.makrokosmos.makrokosmos.dagger.AppComponent
import com.keltapps.makrokosmos.makrokosmos.dagger.DaggerAppComponent


class CosmosApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        val appComponent = createAppComponent()
        appComponent.inject(this)
    }

    private fun createAppComponent(): AppComponent {
        return DaggerAppComponent
                .builder()
                .application(this)
                .build()
    }
}