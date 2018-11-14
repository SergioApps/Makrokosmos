package com.keltapps.makrokosmos.main.viewmodel

import androidx.lifecycle.*
import com.keltapps.makrokosmos.R
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

    override val title = MutableLiveData<String>()
    override val zodiacSignName = MutableLiveData<String>()
    override val isPlaying = MutableLiveData<Boolean>()
    override val openSongDetail = SingleLiveEvent<String>()
    private var currentSongId: String? = null
    override val destination = MutableLiveData<Int>()
    override val isSongDetail: LiveData<Boolean> = Transformations.map(destination) { it -> it == R.id.songDetailFragment }
    override val isStopped = MutableLiveData<Boolean>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun connectListener() {
        audioRepository.start()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun disconnectListener() {
        audioRepository.stop()
        isStopped.value = true
    }

    init {
        subscribeToSongPlaying()
        subscribeToPlayingState()
        mediaSeekBarViewModel.initialize()
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
                isStopped.value = false
                isPlaying.value = true
            }
            PlayingState.Paused -> {
                isStopped.value = false
                isPlaying.value = false
            }
            PlayingState.Stopped -> {
                isStopped.value = true
                isPlaying.value = false
            }
        }
    }

    override fun openSongDetail() {
        currentSongId?.let { openSongDetail.value = it }
    }

    override fun onCleared() {
        audioViewModel.clean()
        super.onCleared()
    }
}