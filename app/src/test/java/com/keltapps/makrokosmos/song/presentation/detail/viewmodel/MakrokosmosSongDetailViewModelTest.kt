package com.keltapps.makrokosmos.song.presentation.detail.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.keltapps.makrokosmos.audio.client.domain.repository.AudioRepository
import com.keltapps.makrokosmos.song.data.repository.RxSchedulersOverrideRule
import com.keltapps.makrokosmos.song.domain.entity.*
import com.keltapps.makrokosmos.song.domain.iteractor.GetSongPlayingUseCase
import com.keltapps.makrokosmos.song.domain.repository.CDRepository
import com.keltapps.makrokosmos.song.presentation.ZodiacSignViewModel
import io.reactivex.Single
import org.junit.*
import org.junit.rules.TestRule
import org.mockito.*
import org.mockito.Mockito.*

class MakrokosmosSongDetailViewModelTest {

    private companion object {
        const val MEDIA_ID = "mediaId"
        const val TITLE = "title"
        const val SUBTITLE = "subtitle"
        const val DURATION = 189
        const val ZODIAC_NAME = "aries"
    }

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    @get:Rule
    val overrideSchedulersRule = RxSchedulersOverrideRule()

    private lateinit var sut: MakrokosmosSongDetailViewModel

    @Mock
    private lateinit var zodiacSignViewModel: ZodiacSignViewModel
    @Mock
    private lateinit var getSongPlayingUseCase: GetSongPlayingUseCase
    @Mock
    private lateinit var audioRepository: AudioRepository
    @Mock
    private lateinit var cdRepository: CDRepository

    @Mock
    private lateinit var mockSong: Song
    @Mock
    private lateinit var mockZodiacSign: ZodiacSign

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = MakrokosmosSongDetailViewModel(
                zodiacSignViewModel,
                getSongPlayingUseCase,
                audioRepository,
                cdRepository
        )
        with(mockSong) {
            `when`(title).thenReturn(TITLE)
            `when`(subTitle).thenReturn(SUBTITLE)
            `when`(durationInSeconds).thenReturn(DURATION)
            `when`(zodiacSign).thenReturn(mockZodiacSign)
            //`when`(zodiacSign).thenReturn(ZodiacSign.Aries(ZODIAC_NAME))
        }
        `when`(cdRepository.getSong(MEDIA_ID)).thenReturn(Single.just(mockSong))
    }

    @Test
    fun initialize_should_initializeZodiacSignViewModel() {

        sut.initialize(MEDIA_ID)

        verify(zodiacSignViewModel).initialize(mockZodiacSign)
    }

    @Test
    fun initialize_should_setTitleAndSubTitleObservables() {

        sut.initialize(MEDIA_ID)

        assertThat(sut.title.value).isEqualTo(TITLE)
        assertThat(sut.subTitle.value).isEqualTo(SUBTITLE)
    }
}