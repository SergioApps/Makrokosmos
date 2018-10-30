package com.keltapps.makrokosmos.info.presentation.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.keltapps.makrokosmos.base.presentation.viewmodel.MakrokosmosBaseViewModel
import com.keltapps.makrokosmos.info.domain.entity.Info
import com.keltapps.makrokosmos.info.domain.iteractor.GetAboutInfoUseCase
import com.keltapps.makrokosmos.info.domain.iteractor.GetAuthorInfoUseCase
import com.keltapps.makrokosmos.info.domain.iteractor.GetInterpreterInfoUseCase
import com.keltapps.makrokosmos.info.presentation.model.InfoScreen
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MakrokosmosInfoViewModel @Inject constructor(
        private val aboutInfoUseCase: GetAboutInfoUseCase,
        private val authorInfoUseCase: GetAuthorInfoUseCase,
        private val interpreterInfoUseCase: GetInterpreterInfoUseCase
) : MakrokosmosBaseViewModel(), InfoViewModel {

    override val image = MutableLiveData<Int>()
    override val title = MutableLiveData<String>()
    override val body = MutableLiveData<String>()

    override fun initialize(infoScreen: InfoScreen) {
        getInfo(infoScreen)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { setInfoScreen(it) }
                .addDisposable()
    }

    private fun getInfo(infoScreen: InfoScreen): Observable<Info> {
        return when (infoScreen) {
            is InfoScreen.AboutScreen -> aboutInfoUseCase.execute()
            is InfoScreen.AuthorScreen -> authorInfoUseCase.execute()
            is InfoScreen.InterpreterScreen -> interpreterInfoUseCase.execute()
        }
    }

    private fun setInfoScreen(info: Info) {
        image.value = info.image
        title.value = info.title
        body.value = info.body
    }
}