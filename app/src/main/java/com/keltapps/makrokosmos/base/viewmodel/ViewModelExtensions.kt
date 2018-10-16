package com.keltapps.makrokosmos.base.viewmodel

import io.reactivex.disposables.Disposable

fun Disposable.addDisposable(baseViewModel: BaseViewModel) {
    baseViewModel.addDisposable(this)
}