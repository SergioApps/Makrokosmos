package com.keltapps.makrokosmos.song.presentation.detail.viewmodel

import android.animation.ValueAnimator
import android.view.animation.LinearInterpolator
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.keltapps.makrokosmos.audio.client.domain.entity.PlayingState
import com.keltapps.makrokosmos.audio.client.domain.repository.AudioRepository
import com.keltapps.makrokosmos.base.presentation.viewmodel.MakrokosmosBaseViewModel
import com.keltapps.makrokosmos.song.domain.entity.Song
import com.keltapps.makrokosmos.song.domain.iteractor.GetSongPlayingUseCase
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

    override fun initialize(song: Song) {
        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.addUpdateListener(this)
        duration.value = song.durationInSeconds.formatTime()
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
            valueAnimator.cancel()
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