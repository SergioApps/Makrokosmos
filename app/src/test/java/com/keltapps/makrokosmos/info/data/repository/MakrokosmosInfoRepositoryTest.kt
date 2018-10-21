package com.keltapps.makrokosmos.info.data.repository

import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.base.resourceprovider.ResourceProvider
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MakrokosmosInfoRepositoryTest {

    private companion object {
        const val ABOUT_TITLE = "aboutTitle"
        const val ABOUT_BODY = "aboutBody"
        const val AUTHOR_TITLE = "authorTitle"
        const val AUTHOR_BODY = "authorBody"
        const val INTERPRETER_TITLE = "interpreterTitle"
        const val INTERPRETER_BODY = "interpreterBody"
    }

    private lateinit var sut: MakrokosmosInfoRepository

    @Mock
    private lateinit var resourceProvider: ResourceProvider

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = MakrokosmosInfoRepository(
                resourceProvider
        )
        mockResources()
    }

    private fun mockResources() {
        with(resourceProvider) {
            `when`(getString(R.string.infoAbout_title)).thenReturn(ABOUT_TITLE)
            `when`(getString(R.string.infoAbout_body)).thenReturn(ABOUT_BODY)
            `when`(getString(R.string.infoAuthor_title)).thenReturn(AUTHOR_TITLE)
            `when`(getString(R.string.infoAuthor_body)).thenReturn(AUTHOR_BODY)
            `when`(getString(R.string.infoInterpreter_title)).thenReturn(INTERPRETER_TITLE)
            `when`(getString(R.string.infoInterpreter_body)).thenReturn(INTERPRETER_BODY)
        }
    }

    @Test
    fun getAboutInfo_should_returnAboutInfo() {
        val testObserver = sut.getAboutInfo().test()

        testObserver.assertValue { it.title == ABOUT_TITLE }
                .assertValue { it.body == ABOUT_BODY }
                .assertValue { it.image == R.drawable.about }
    }

    @Test
    fun getAuthorInfo_should_returnAuthorInfo() {
        val testObserver = sut.getAuthorInfo().test()

        testObserver.assertValue { it.title == AUTHOR_TITLE }
                .assertValue { it.body == AUTHOR_BODY }
                .assertValue { it.image == R.drawable.author }
    }

    @Test
    fun getInterpreterInfo_should_returnInfo() {
        val testObserver = sut.getInterpreterInfo().test()

        testObserver.assertValue { it.title == INTERPRETER_TITLE }
                .assertValue { it.body == INTERPRETER_BODY }
                .assertValue { it.image == R.drawable.interpreter }
    }
}