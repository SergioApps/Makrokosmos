package com.keltapps.makrokosmos.audio.service.data.player.audiofocus

import com.keltapps.makrokosmos.audio.service.data.player.MediaPlayerAdapter

interface AudioFocusHelper {
    fun initialize(playerAdapter: MediaPlayerAdapter)
    fun requestAudioFocus():Boolean
    fun abandonAudioFocus()
    val playOnAudioFocus: Boolean
}