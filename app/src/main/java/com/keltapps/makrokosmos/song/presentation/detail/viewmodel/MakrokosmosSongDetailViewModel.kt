package com.keltapps.makrokosmos.song.presentation.detail.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.graphics.drawable.Drawable
import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.audio.client.domain.entity.PlayingState
import com.keltapps.makrokosmos.audio.client.domain.repository.AudioRepository
import com.keltapps.makrokosmos.base.presentation.viewmodel.MakrokosmosBaseViewModel
import com.keltapps.makrokosmos.song.domain.entity.Element
import com.keltapps.makrokosmos.song.domain.entity.Song
import com.keltapps.makrokosmos.song.domain.iteractor.GetSongPlayingUseCase
import com.keltapps.makrokosmos.song.domain.repository.CDRepository
import com.keltapps.makrokosmos.song.presentation.ZodiacSignViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

class MakrokosmosSongDetailViewModel @Inject constructor(
        override val zodiacSignViewModel: ZodiacSignViewModel,
        private val getSongPlayingUseCase: GetSongPlayingUseCase,
        private val audioRepository: AudioRepository,
        private val cdRepository: CDRepository,
        @Named("timeFormat") private val timeFormat: String,
        @Named("airColor") private val airColor: Int,
        @Named("fireColor") private val fireColor: Int,
        @Named("earthColor") private val earthColor: Int,
        @Named("waterColor") private val waterColor: Int,
        @Named("playToPause") private val playToPause: Drawable,
        @Named("pauseToPlay") private val pauseToPlay: Drawable
) : MakrokosmosBaseViewModel(), SongDetailViewModel {

    override val title = MutableLiveData<String>()
    override val subTitle = MutableLiveData<String>()
    override val zodiacSignColor = MutableLiveData<Int>()
    override val zodiacSignName = MutableLiveData<String>()
    override val duration = MutableLiveData<String>()
    override val playOrPauseIcon = MutableLiveData<Drawable>()

    override fun initialize(songId: String) {
        zodiacSignColor.value = R.color.earth_dark
        cdRepository.getSong(songId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::handleCDSubscription)
                .addDisposable()
        audioRepository.getPlayingState()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::handlePlayingState)
                .addDisposable()
    }

    private fun handleCDSubscription(song: Song) {
        setLiveData(song)
        getSongPlayingUseCase.execute()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::setLiveData)
                .addDisposable()
        audioRepository.play(song)
    }

    private fun setLiveData(song: Song) {
        zodiacSignViewModel.initialize(song.zodiacSign)
        title.value = song.title
        subTitle.value = song.subTitle
        zodiacSignName.value = song.zodiacSign.name
        setZodiacSignColor(song.zodiacSign.element)
        setDuration(song.durationInSeconds)
    }

    private fun setZodiacSignColor(element: Element) {
        zodiacSignColor.value = when (element) {
            Element.Air -> airColor
            Element.Fire -> fireColor
            Element.Earth -> earthColor
            Element.Water -> waterColor
        }
    }

    private fun setDuration(durationInSeconds: Int) {
        duration.value = String.format(timeFormat, durationInSeconds / 60, durationInSeconds % 60)
    }

    private fun handlePlayingState(playingState: PlayingState) {
        playOrPauseIcon.value = if (playingState is PlayingState.Playing) {
            playToPause
        } else {
            pauseToPlay
        }
    }

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