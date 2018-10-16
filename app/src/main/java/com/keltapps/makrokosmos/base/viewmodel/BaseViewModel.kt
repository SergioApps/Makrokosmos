package com.keltapps.makrokosmos.base.viewmodel

import io.reactivex.disposables.Disposable

interface BaseViewModel {
    fun addDisposable(disposable: Disposable)
}