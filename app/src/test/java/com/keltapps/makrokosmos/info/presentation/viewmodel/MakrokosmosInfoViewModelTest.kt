package com.keltapps.makrokosmos.info.presentation.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.keltapps.makrokosmos.info.domain.entity.Info
import com.keltapps.makrokosmos.info.domain.iteractor.GetAboutInfoUseCase
import com.keltapps.makrokosmos.info.domain.iteractor.GetAuthorInfoUseCase
import com.keltapps.makrokosmos.info.domain.iteractor.GetInterpreterInfoUseCase
import com.keltapps.makrokosmos.info.presentation.model.InfoScreen
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MakrokosmosInfoViewModelTest {

    private companion object {
        const val IMAGE = 1
        const val TITLE = "title"
        const val BODY = "body"
    }

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var sut: MakrokosmosInfoViewModel

    @Mock
    private lateinit var getAboutInfoUseCase: GetAboutInfoUseCase
    @Mock
    private lateinit var getAuthorInfoUseCase: GetAuthorInfoUseCase
    @Mock
    private lateinit var getInterpreterInfoUseCase: GetInterpreterInfoUseCase

    @Mock
    private lateinit var mockInfo: Info

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = MakrokosmosInfoViewModel(
                getAboutInfoUseCase,
                getAuthorInfoUseCase,
                getInterpreterInfoUseCase
        )
        mockInfo()
    }

    private fun mockInfo() {
        with(mockInfo) {
            `when`(image).thenReturn(IMAGE)
            `when`(title).thenReturn(TITLE)
            `when`(body).thenReturn(BODY)
        }
    }

    @Test
    fun initialize_should_setAboutInfo_when_screenIsAbout() {
        `when`(getAboutInfoUseCase.execute()).thenReturn(Observable.just(mockInfo))

        sut.initialize(InfoScreen.AboutScreen)

        assertValues()
    }

    @Test
    fun initialize_should_setAuthorInfo_when_screenIsAuthor() {
        `when`(getAuthorInfoUseCase.execute()).thenReturn(Observable.just(mockInfo))

        sut.initialize(InfoScreen.AuthorScreen)

        assertValues()
    }

    @Test
    fun initialize_should_setInterpreterInfo_when_screenIsInterpreter() {
        `when`(getInterpreterInfoUseCase.execute()).thenReturn(Observable.just(mockInfo))

        sut.initialize(InfoScreen.InterpreterScreen)

        assertValues()
    }

    private fun assertValues() {
        assertThat(sut.image.value).isEqualTo(IMAGE)
        assertThat(sut.title.value).isEqualTo(TITLE)
        assertThat(sut.body.value).isEqualTo(BODY)
    }
}