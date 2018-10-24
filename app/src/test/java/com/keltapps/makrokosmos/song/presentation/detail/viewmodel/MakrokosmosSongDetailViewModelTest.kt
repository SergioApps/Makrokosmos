package com.keltapps.makrokosmos.song.presentation.detail.viewmodel

import com.google.common.truth.Truth.assertThat
import com.keltapps.makrokosmos.song.presentation.ZodiacSignViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MakrokosmosSongDetailViewModelTest {

    private lateinit var sut: MakrokosmosSongDetailViewModel

    @Mock
    private lateinit var zodiacSignViewModel: ZodiacSignViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = MakrokosmosSongDetailViewModel(
                zodiacSignViewModel
        )
    }

    @Test
    fun initialize_should_initializeZodiacSignViewModel() {
        //`when`(mockSong.zodiacSign).thenReturn(ZodiacSign.Aries(MakrokosmosSongItemViewModelTest.ARIES))

        assertThat(true).isFalse()
        /*   sut.initialize(mockSong)

           verify(zodiacSignViewModel).initialize(mockSong.zodiacSign)*/
    }
}