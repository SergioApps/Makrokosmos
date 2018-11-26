package com.keltapps.musicalzodiacpiano.song.presentation.detail.viewmodel

import android.animation.ValueAnimator
import android.view.animation.LinearInterpolator
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.keltapps.musicalzodiacpiano.audio.client.domain.entity.PlayingState
import com.keltapps.musicalzodiacpiano.audio.client.domain.repository.AudioRepository
import com.keltapps.musicalzodiacpiano.base.presentation.viewmodel.MakrokosmosBaseViewModel
import com.keltapps.musicalzodiacpiano.song.domain.entity.Song
import com.keltapps.musicalzodiacpiano.song.domain.iteractor.GetSongPlayingUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

class MakrokosmosMediaSeekBarViewModel @Inject constructor(
        private val audioRepository: AudioRepository,
        private val valueAnimator: ValueAnimator,
        private val getSongPlayingUseCase: GetSongPlayingUseCase,
        @Named("timeFormat") private val timeFormat: String
) : MakrokosmosBaseViewModel(), MediaSeekBarViewModel, ValueAnimator.AnimatorUpdateListener {

    override val progress = MutableLiveData<Int>()
    override val maxValue = MutableLiveData<Int>()
    override val progressFormatted: LiveData<String> = Transformations.map(progress) { it -> (it / 1000).formatTime() }
    override val duration = MutableLiveData<String>()

    init {
        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.addUpdateListener(this)
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
        maxValue.value = song.durationInSeconds.secondsToMilliseconds()
        progress.value = audioRepository.getCurrentPositionInSeconds().toInt().secondsToMilliseconds()
        duration.value = song.durationInSeconds.formatTime()
        handleAnimation()
    }

    private fun Int.formatTime() = String.format(timeFormat, this / 60, this % 60)

    private fun handleAnimation() {
        handleAnimation(audioRepository.getCurrentPlayingState())
    }

    private fun handleAnimation(state: PlayingState) {
        if (state is PlayingState.Playing) {
            maxValue.value?.let { maxValue ->
                valueAnimator.setIntValues(progress.value ?: 0, maxValue)
                valueAnimator.duration = getRemainingTime(maxValue)
                valueAnimator.start()
            }
        } else {
            valueAnimator.pause()
        }
    }

    private fun getRemainingTime(maxValue: Int): Long {
        return (maxValue - (progress.value ?: 0)).toLong()
    }

    private fun Int?.secondsToMilliseconds() = (this ?: 0) * 1000

    private fun subscribeToPlayingState() {
        audioRepository.getPlayingState()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::handleAnimation)
                .addDisposable()
    }

    override fun startTracking() {
        valueAnimator.pause()
    }

    override fun stopTracking() {
        audioRepository.seekTo(progress.value?.toLong() ?: 0)
        handleAnimation()
    }

    override fun onAnimationUpdate(valueAnimator: ValueAnimator) {
        progress.value = valueAnimator.animatedValue as Int
    }
}