package com.keltapps.makrokosmos.presentation.songList.viewModel


import android.arch.lifecycle.MutableLiveData
import com.keltapps.makrokosmos.domain.entity.*
import com.keltapps.makrokosmos.domain.iteractor.GetCDUseCase
import com.keltapps.makrokosmos.presentation.base.viewModel.MakrokosmosBaseViewModel
import com.keltapps.makrokosmos.presentation.extension.addDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MakrokosmosSongListViewModel @Inject constructor(
        private val cdUseCase: GetCDUseCase
) : MakrokosmosBaseViewModel(), SongListViewModel {

    override val cdListItems = MutableLiveData<List<Song>>()
    override val title = MutableLiveData<String>()

    override fun initialize(volumeIndex: Int) {
        cdUseCase.execute()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { handleSubscription(it, volumeIndex) }
                .addDisposable(this)
    }

    private fun handleSubscription(cd: CD, volumeIndex: Int) {
        cdListItems.value = cd.volumeList[volumeIndex].songList
        title.value = cd.volumeList[volumeIndex].title
    }
}