package com.keltapps.makrokosmos.audio.service.data.player.audiofocus

import com.keltapps.makrokosmos.audio.service.data.player.MakrokosmosMediaPlayerAdapter

interface AudioFocusHelper {
    fun initialize(playerAdapterMakrokosmos: MakrokosmosMediaPlayerAdapter)
    fun requestAudioFocus():Boolean
    fun abandonAudioFocus()
    val playOnAudioFocus: Boolean
}