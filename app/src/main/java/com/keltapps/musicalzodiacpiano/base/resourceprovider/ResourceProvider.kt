package com.keltapps.musicalzodiacpiano.base.resourceprovider

import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes stringId: Int): String
}