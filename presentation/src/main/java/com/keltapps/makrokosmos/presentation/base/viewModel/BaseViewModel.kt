package com.keltapps.makrokosmos.presentation.base.viewModel

import io.reactivex.disposables.Disposable

interface BaseViewModel {
    fun addDisposable(disposable: Disposable)
    fun initialize()
    fun cleanUp()
}