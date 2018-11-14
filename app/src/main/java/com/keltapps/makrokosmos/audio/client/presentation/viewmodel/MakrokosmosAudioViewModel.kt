package com.keltapps.makrokosmos.audio.client.presentation.viewmodel

import com.keltapps.makrokosmos.audio.client.domain.entity.PlayingState
import com.keltapps.makrokosmos.audio.client.domain.repository.AudioRepository
import com.keltapps.makrokosmos.base.presentation.viewmodel.MakrokosmosBaseViewModel
import com.keltapps.makrokosmos.song.presentation.detail.viewmodel.MakrokosmosMediaSeekBarViewModel
import com.keltapps.makrokosmos.song.presentation.detail.viewmodel.MediaSeekBarViewModel
import javax.inject.Inject

class MakrokosmosAudioViewModel @Inject constructor(
        private val audioRepository: AudioRepository,
        private val mediaSeekBarViewModel: MediaSeekBarViewModel
) : MakrokosmosBaseViewModel(), AudioViewModel {

    private var currentSongId: String? = null

    init {
        audioRepository.getSongIdPlaying()
                .subscribe { currentSongId = it }
                .addDisposable()
    }

    override fun playOrPause() {
        when (audioRepository.getCurrentPlayingState()) {
            PlayingState.Playing -> audioRepository.pause()
            PlayingState.Paused -> audioRepository.continuePlaying()
            PlayingState.Stopped -> {
                currentSongId?.let {
                    audioRepository.play(it)
                    mediaSeekBarViewModel.progress.value?.let { progress ->
                        audioRepository.seekTo(progress.toLong())
                    }
                }
            }
        }
    }

    override fun skipToNext() {
        audioRepository.skipToNext()
    }

    override fun skipToPrevious() {
        audioRepository.skipToPrevious()
    }

    override fun clean() {
        super.onCleared()
    }
}