package com.keltapps.makrokosmos.base.resourceprovider

import android.support.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes stringId: Int): String
}