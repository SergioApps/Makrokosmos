package com.keltapps.makrokosmos.song.data.resourceprovider

import android.support.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes stringId: Int): String
}