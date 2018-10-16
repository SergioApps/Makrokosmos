package com.keltapps.makrokosmos.song.data.resourceprovider

import android.content.Context
import javax.inject.Inject

class MakrokosmosResourceProvider @Inject constructor(
        private val applicationContext: Context
) : ResourceProvider {

    override fun getString(stringId: Int): String {
        return applicationContext.getString(stringId)
    }
}