package com.keltapps.makrokosmos.base.viewmodel

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.*

abstract class MakrokosmosBaseViewModel : ViewModel(), BaseViewModel {

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun Disposable.addDisposable() {
        compositeDisposable.add(this)
    }
}