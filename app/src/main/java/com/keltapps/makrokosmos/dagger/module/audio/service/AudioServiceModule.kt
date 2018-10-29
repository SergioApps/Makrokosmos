package com.keltapps.makrokosmos.dagger.module.audio.service

import com.keltapps.makrokosmos.MainActivity
import com.keltapps.makrokosmos.audio.service.data.manager.*
import com.keltapps.makrokosmos.audio.service.data.notification.*
import com.keltapps.makrokosmos.dagger.scope.ServiceScope
import dagger.*

@Module
class AudioServiceModule {

    @Provides
    @ServiceScope
    fun provideMediaNotificationManager(
            notificationManager: MakrokosmosMediaNotificationManager
    ): MediaNotificationManager {
        return notificationManager
    }

    @Provides
    @ServiceScope
    fun provideActivityToOpen(): Class<*> {
        return MainActivity::class.java
    }

    @Provides
    @ServiceScope
    fun provideServiceManager(serviceManager: MakrokosmosServiceManager): ServiceManager {
        return serviceManager
    }
}