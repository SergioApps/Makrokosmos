package com.keltapps.makrokosmos.audio.service.player

interface AudioNoisyReceiver {
    fun initialize(mediaPlayerAdapter: MediaPlayerAdapter)
    fun register()
    fun unregister()
}