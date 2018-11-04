package com.keltapps.makrokosmos.song.presentation.detail.viewmodel

import androidx.lifecycle.MutableLiveData
import android.graphics.drawable.Drawable
import com.keltapps.makrokosmos.audio.client.domain.entity.PlayingState
import com.keltapps.makrokosmos.audio.client.domain.repository.AudioRepository
import com.keltapps.makrokosmos.base.presentation.SingleLiveEvent
import com.keltapps.makrokosmos.base.presentation.viewmodel.MakrokosmosBaseViewModel
import com.keltapps.makrokosmos.song.domain.entity.*
import com.keltapps.makrokosmos.song.domain.iteractor.GetSongPlayingUseCase
import com.keltapps.makrokosmos.song.domain.repository.CDRepository
import com.keltapps.makrokosmos.song.presentation.ZodiacSignViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.*

class MakrokosmosSongDetailViewModel @Inject constructor(
        override val zodiacSignViewModel: ZodiacSignViewModel,
        override val mediaSeekBarViewModel: MediaSeekBarViewModel,
        private val getSongPlayingUseCase: GetSongPlayingUseCase,
        private val audioRepository: AudioRepository,
        private val cdRepository: CDRepository,
        @Named("airColor") private val airColor: Int,
        @Named("fireColor") private val fireColor: Int,
        @Named("earthColor") private val earthColor: Int,
        @Named("waterColor") private val waterColor: Int
) : MakrokosmosBaseViewModel(), SongDetailViewModel {

    override val title = MutableLiveData<String>()
    override val subTitle = MutableLiveData<String>()
    override val zodiacSignColor = MutableLiveData<Int>()
    override val zodiacSignName = MutableLiveData<String>()
    override val isPlaying = MutableLiveData<Boolean>()

    override fun initialize(songId: String) {
        zodiacSignColor.value = airColor
        mediaSeekBarViewModel.initialize()
        getSongPlaying(songId)
        subscribeToPlayingState()
    }

    private fun getSongPlaying(songId: String) {
        cdRepository.getSong(songId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::handleCDSubscription)
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
    }

    private fun setZodiacSignColor(element: Element) {
        zodiacSignColor.value = when (element) {
            Element.Air -> airColor
            Element.Fire -> fireColor
            Element.Earth -> earthColor
            Element.Water -> waterColor
        }
    }

    private fun subscribeToPlayingState() {
        audioRepository.getPlayingState()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::handlePlayingState)
                .addDisposable()
    }

    private fun handlePlayingState(playingState: PlayingState) {
        isPlaying.value = playingState is PlayingState.Playing
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