package com.keltapps.makrokosmos.audio.service.data.player

interface AudioNoisyReceiver {
    fun initialize(mediaPlayerAdapter: MediaPlayerAdapter)
    fun register()
    fun unregister()
}