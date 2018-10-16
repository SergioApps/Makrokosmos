package com.keltapps.makrokosmos.makrokosmos.song.data.resourceprovider

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.song.data.resourceprovider.MakrokosmosResourceProvider
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MakrokosmosResourceProviderTest {

    private val resourceProvider = MakrokosmosResourceProvider(InstrumentationRegistry.getTargetContext())
    private val cdTitle = "Makrokosmos"

    @Test
    fun getString_should_returnStringFromResources() {

        val result = resourceProvider.getString(R.string.title_cd_title)

        assertEquals(result, cdTitle)
    }
}