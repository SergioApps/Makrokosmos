package com.keltapps.makrokosmos.presentation.base.viewModel

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.Disposable

interface BaseViewModel {
    fun addDisposable(disposable: Disposable)
    fun cleanUp()
}