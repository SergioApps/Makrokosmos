package com.keltapps.musicalzodiacpiano.song.data.resourceprovider

import androidx.test.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.keltapps.musicalzodiacpiano.R
import com.keltapps.musicalzodiacpiano.base.resourceprovider.MakrokosmosResourceProvider
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MakrokosmosResourceProviderTest {

    private val resourceProvider = MakrokosmosResourceProvider(InstrumentationRegistry.getTargetContext())
    private val cdTitle = "Musical Zodiac"

    @Test
    fun getString_should_returnStringFromResources() {

        val result = resourceProvider.getString(R.string.title_cd_title)

        assertEquals(result, cdTitle)
    }
}