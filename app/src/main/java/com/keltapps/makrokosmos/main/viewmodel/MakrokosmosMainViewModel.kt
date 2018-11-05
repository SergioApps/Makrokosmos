package com.keltapps.makrokosmos.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.keltapps.makrokosmos.audio.client.domain.entity.PlayingState
import com.keltapps.makrokosmos.audio.client.domain.repository.AudioRepository
import com.keltapps.makrokosmos.audio.client.presentation.viewmodel.AudioViewModel
import com.keltapps.makrokosmos.base.presentation.SingleLiveEvent
import com.keltapps.makrokosmos.base.presentation.viewmodel.MakrokosmosBaseViewModel
import com.keltapps.makrokosmos.song.domain.entity.Song
import com.keltapps.makrokosmos.song.domain.iteractor.GetSongPlayingUseCase
import com.keltapps.makrokosmos.song.presentation.detail.viewmodel.MediaSeekBarViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MakrokosmosMainViewModel @Inject constructor(
        override val mediaSeekBarViewModel: MediaSeekBarViewModel,
        private val getSongPlayingUseCase: GetSongPlayingUseCase,
        private val audioRepository: AudioRepository,
        override val audioViewModel: AudioViewModel
) : MakrokosmosBaseViewModel(), MainViewModel {

    override val isVisible = MutableLiveData<Boolean>()
    override val title = MutableLiveData<String>()
    override val zodiacSignName = MutableLiveData<String>()
    override val isPlaying = MutableLiveData<Boolean>()
    override val openSongDetail = SingleLiveEvent<String>()
    private var currentSongId: String? = null

    override fun initialize() {
        subscribeToSongPlaying()
        subscribeToPlayingState()
    }

    private fun subscribeToSongPlaying() {
        getSongPlayingUseCase.execute()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::handleSongReceived)
                .addDisposable()
    }

    private fun handleSongReceived(song: Song) {
        currentSongId = song.id
        mediaSeekBarViewModel.initialize(song)
        title.value = song.title
        zodiacSignName.value = song.zodiacSign.name
    }

    private fun subscribeToPlayingState() {
        audioRepository.getPlayingState()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::handleStateReceived)
                .addDisposable()
    }

    private fun handleStateReceived(state: PlayingState) {
        when (state) {
            PlayingState.Playing -> {
                isVisible.value = true
                isPlaying.value = true
            }
            PlayingState.Paused -> {
                isVisible.value = true
                isPlaying.value = false
            }
            PlayingState.Stopped -> {
                isVisible.value = false
                isPlaying.value = false
            }
        }
    }

    override fun openSongDetail() {
        currentSongId?.let { openSongDetail.value = it }
    }
}