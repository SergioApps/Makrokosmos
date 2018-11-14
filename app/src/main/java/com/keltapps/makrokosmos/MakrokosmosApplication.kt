package com.keltapps.makrokosmos

import android.app.*
import com.keltapps.makrokosmos.dagger.*
import com.squareup.leakcanary.LeakCanary
import dagger.android.*
import javax.inject.Inject


class MakrokosmosApplication : Application(), HasActivityInjector, HasServiceInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var serviceDispatchingAndroidInjector: DispatchingAndroidInjector<Service>

    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchingAndroidInjector
    override fun serviceInjector(): AndroidInjector<Service> = serviceDispatchingAndroidInjector

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) return
        LeakCanary.install(this)
        appComponent = createAppComponent()
        appComponent.inject(this)
    }

    private fun createAppComponent(): AppComponent {
        return DaggerAppComponent
                .builder()
                .application(this)
                .build()
    }
}