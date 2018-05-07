package com.keltapps.makrokosmos.presentation.base.viewModel

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class MakrokosmosBaseViewModel(private val compositeDisposable: CompositeDisposable)
    : ViewModel(), BaseViewModel {

    constructor() : this(CompositeDisposable())

    override fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}