package com.keltapps.makrokosmos.data.resourceProvider

import android.support.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes stringId: Int): String
}