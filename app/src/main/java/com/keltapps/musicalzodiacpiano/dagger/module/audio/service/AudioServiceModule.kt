package com.keltapps.musicalzodiacpiano.dagger.module.audio.service

import com.keltapps.musicalzodiacpiano.main.view.MainActivity
import com.keltapps.musicalzodiacpiano.audio.service.data.manager.*
import com.keltapps.musicalzodiacpiano.audio.service.data.notification.*
import com.keltapps.musicalzodiacpiano.dagger.scope.ServiceScope
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