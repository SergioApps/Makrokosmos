package com.keltapps.makrokosmos.song.presentation.detail.viewmodel

import android.animation.ValueAnimator
import android.arch.lifecycle.MutableLiveData
import com.keltapps.makrokosmos.audio.client.domain.entity.PlayingState
import com.keltapps.makrokosmos.audio.client.domain.repository.AudioRepository
import com.keltapps.makrokosmos.base.presentation.viewmodel.MakrokosmosBaseViewModel
import com.keltapps.makrokosmos.song.domain.entity.Song
import com.keltapps.makrokosmos.song.domain.iteractor.GetSongPlayingUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MakrokosmosMediaSeekBarViewModel @Inject constructor(
        private val audioRepository: AudioRepository,
        private val valueAnimator: ValueAnimator,
        private val getSongPlayingUseCase: GetSongPlayingUseCase
) : MakrokosmosBaseViewModel(), MediaSeekBarViewModel, ValueAnimator.AnimatorUpdateListener {

    override val progress = MutableLiveData<Int>()
    override val maxValue = MutableLiveData<Int>()

    override fun initialize() {
        subscribeToSongPlaying()
        subscribeToPlayingState()
    }

    private fun subscribeToSongPlaying() {
        getSongPlayingUseCase.execute()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::handleSongChange)
                .addDisposable()
    }

    private fun handleSongChange(song: Song) {
        maxValue.value = song.durationInSeconds
        progress.value = 0
        handleAnimation()
    }

    private fun handleAnimation() {
        handleAnimation(audioRepository.getCurrentPlayingState())
    }

    private fun handleAnimation(state: PlayingState) {
        when (state) {
            PlayingState.Playing -> {
                maxValue.value?.let {
                    valueAnimator.setIntValues(progress.value ?: 0, it)
                    valueAnimator.duration = (it - (progress.value ?: 0)).toLong()
                    valueAnimator.start()
                }
            }
            else -> valueAnimator.cancel()
        }
    }

    private fun subscribeToPlayingState() {
        audioRepository.getPlayingState()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::handleAnimation)
                .addDisposable()
    }

    override fun startTracking() {
        valueAnimator.cancel()
    }

    override fun stopTracking() {
        audioRepository.seekTo(progress.value?.toLong() ?: 0)
        handleAnimation()
    }

    override fun onAnimationUpdate(valueAnimator: ValueAnimator) {
        progress.value = valueAnimator.animatedValue as Int
    }
}