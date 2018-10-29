package com.keltapps.makrokosmos.song.presentation.detail.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.keltapps.makrokosmos.audio.client.domain.repository.AudioRepository
import com.keltapps.makrokosmos.base.presentation.viewmodel.MakrokosmosBaseViewModel
import com.keltapps.makrokosmos.song.domain.entity.Song
import com.keltapps.makrokosmos.song.domain.iteractor.GetSongPlayingUseCase
import com.keltapps.makrokosmos.song.domain.repository.CDRepository
import com.keltapps.makrokosmos.song.presentation.ZodiacSignViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MakrokosmosSongDetailViewModel @Inject constructor(
        override val zodiacSignViewModel: ZodiacSignViewModel,
        private val getSongPlayingUseCase: GetSongPlayingUseCase,
        private val audioRepository: AudioRepository,
        private val cdRepository: CDRepository
) : MakrokosmosBaseViewModel(), SongDetailViewModel {

    override val title = MutableLiveData<String>()
    override val subTitle = MutableLiveData<String>()
    override val colorZodiacSign = MutableLiveData<Int>()
    override val zodiacSignName = MutableLiveData<String>()
    override val duration = MutableLiveData<String>()

    override fun initialize(songId: String) {
        cdRepository.getSong(songId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::handleSubscription)
                .addDisposable()
    }

    private fun handleSubscription(song: Song) {
        zodiacSignViewModel.initialize(song.zodiacSign)
        title.value = song.title
        subTitle.value = song.subTitle
    }

    override fun playOrPause() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun skipToNext() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun skipToPrevious() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}