package com.keltapps.makrokosmos.audio.service

import android.support.v4.media.MediaDescriptionCompat

interface MediaSessionCallbackPresenter {
    fun attach(mediaSessionCallback: MediaSessionCallback)
    fun onAddQueueItem(description: MediaDescriptionCompat)
    fun onRemoveQueueItem(description: MediaDescriptionCompat)
    fun onPrepare()
    fun onPlay()
    fun onSkipToNext()
    fun onSkipToPrevious()
}