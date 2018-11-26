package com.keltapps.musicalzodiacpiano.base.resourceprovider

import android.content.Context
import javax.inject.Inject
import javax.inject.Named

class MakrokosmosResourceProvider @Inject constructor(
        @Named("applicationContext") private val applicationContext: Context
) : ResourceProvider {

    override fun getString(stringId: Int): String {
        return applicationContext.getString(stringId)
    }
}