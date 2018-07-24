package com.keltapps.makrokosmos.presentation.extension

import com.keltapps.makrokosmos.presentation.base.viewModel.BaseViewModel
import io.reactivex.disposables.Disposable

fun Disposable.addDisposable(baseViewModel: BaseViewModel) {
    baseViewModel.addDisposable(this)
}