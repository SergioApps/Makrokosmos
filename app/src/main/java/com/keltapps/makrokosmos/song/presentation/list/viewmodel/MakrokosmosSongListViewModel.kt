package com.keltapps.makrokosmos.song.presentation.list.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.keltapps.makrokosmos.base.presentation.SingleLiveEvent
import com.keltapps.makrokosmos.song.domain.entity.*
import com.keltapps.makrokosmos.song.domain.iteractor.GetCDUseCase
import com.keltapps.makrokosmos.base.presentation.viewmodel.MakrokosmosBaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MakrokosmosSongListViewModel @Inject constructor(
        private val cdUseCase: GetCDUseCase
) : MakrokosmosBaseViewModel(), SongListViewModel {

    override val cdListItems = MutableLiveData<List<Song>>()

    override fun initialize(volumeIndex: Int) {
        cdUseCase.execute()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { handleSubscription(it, volumeIndex) }
                .addDisposable()
    }

    private fun handleSubscription(cd: CD, volumeIndex: Int) {
        cdListItems.value = cd.volumeList[volumeIndex].songList
    }
}