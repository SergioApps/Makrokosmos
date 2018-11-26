package com.keltapps.musicalzodiacpiano.info.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


sealed class InfoScreen : Parcelable {
    @Parcelize
    object AboutScreen : Parcelable, InfoScreen()

    @Parcelize
    object AuthorScreen : Parcelable, InfoScreen()

    @Parcelize
    object InterpreterScreen : Parcelable, InfoScreen()
}