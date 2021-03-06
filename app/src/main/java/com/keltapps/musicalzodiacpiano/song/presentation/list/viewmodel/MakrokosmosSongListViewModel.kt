package com.keltapps.musicalzodiacpiano.song.presentation.list.viewmodel

import androidx.lifecycle.MutableLiveData
import com.keltapps.musicalzodiacpiano.song.domain.entity.*
import com.keltapps.musicalzodiacpiano.song.domain.iteractor.GetCDUseCase
import com.keltapps.musicalzodiacpiano.base.presentation.viewmodel.MakrokosmosBaseViewModel
import com.keltapps.musicalzodiacpiano.song.presentation.list.annotation.ProvideVolumeIndex
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MakrokosmosSongListViewModel @Inject constructor(
        cdUseCase: GetCDUseCase,
        @ProvideVolumeIndex volumeIndex: Int
) : MakrokosmosBaseViewModel(), SongListViewModel {

    override val cdListItems = MutableLiveData<List<Song>>()

    init {
        cdUseCase.execute()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { handleSubscription(it, volumeIndex) }
                .addDisposable()
    }

    private fun handleSubscription(cd: CD, volumeIndex: Int) {
        cdListItems.value = cd.volumeList[volumeIndex].songList
    }
}