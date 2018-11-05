package com.keltapps.makrokosmos.audio.client.presentation.viewmodel

import com.keltapps.makrokosmos.audio.client.domain.entity.PlayingState
import com.keltapps.makrokosmos.audio.client.domain.repository.AudioRepository
import javax.inject.Inject

class MakrokosmosAudioViewModel @Inject constructor(
        private val audioRepository: AudioRepository
) : AudioViewModel {

    override fun playOrPause() {
        if (audioRepository.getCurrentPlayingState() is PlayingState.Playing) {
            audioRepository.pause()
        } else {
            audioRepository.continuePlaying()
        }
    }

    override fun skipToNext() {
        audioRepository.skipToNext()
    }

    override fun skipToPrevious() {
        audioRepository.skipToPrevious()
    }
}