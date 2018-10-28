package com.keltapps.makrokosmos.audio.service.player.audiofocus

import com.keltapps.makrokosmos.audio.service.player.MakrokosmosMediaPlayerAdapter

interface AudioFocusHelper {
    fun initialize(playerAdapterMakrokosmos: MakrokosmosMediaPlayerAdapter)
    fun requestAudioFocus():Boolean
    fun abandonAudioFocus()
    val playOnAudioFocus: Boolean
}