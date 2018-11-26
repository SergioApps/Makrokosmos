package com.keltapps.musicalzodiacpiano.song.presentation.detail.viewmodel

import androidx.lifecycle.MutableLiveData
import com.keltapps.musicalzodiacpiano.audio.client.domain.entity.PlayingState
import com.keltapps.musicalzodiacpiano.audio.client.domain.repository.AudioRepository
import com.keltapps.musicalzodiacpiano.audio.client.presentation.viewmodel.AudioViewModel
import com.keltapps.musicalzodiacpiano.base.presentation.viewmodel.MakrokosmosBaseViewModel
import com.keltapps.musicalzodiacpiano.song.domain.entity.*
import com.keltapps.musicalzodiacpiano.song.domain.iteractor.GetSongPlayingUseCase
import com.keltapps.musicalzodiacpiano.song.domain.repository.CDRepository
import com.keltapps.musicalzodiacpiano.song.presentation.ZodiacSignViewModel
import com.keltapps.musicalzodiacpiano.song.presentation.detail.annotation.ProvideSongId
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
        @Named("waterColor") private val waterColor: Int,
        override val audioViewModel: AudioViewModel,
        @ProvideSongId songId: String
) : MakrokosmosBaseViewModel(), SongDetailViewModel {

    override val title = MutableLiveData<String>()
    override val subTitle = MutableLiveData<String>()
    override val zodiacSignColor = MutableLiveData<Int>()
    override val zodiacSignName = MutableLiveData<String>()
    override val isPlaying = MutableLiveData<Boolean>()

    init {
        zodiacSignColor.value = airColor
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
        audioRepository.play(song.id)
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

    override fun onCleared() {
        audioViewModel.clean()
        super.onCleared()
    }
}