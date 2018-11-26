package com.keltapps.musicalzodiacpiano.audio.service.domain.iteractor

import android.support.v4.media.MediaBrowserCompat
import com.keltapps.musicalzodiacpiano.audio.service.domain.repository.MusicLibraryRepository
import com.keltapps.musicalzodiacpiano.song.domain.entity.CD
import com.keltapps.musicalzodiacpiano.song.domain.repository.CDRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetMediaItemsUseCaseTest {

    private lateinit var sut: GetMediaItemsUseCase

    @Mock
    private lateinit var cdRepository: CDRepository
    @Mock
    private lateinit var musicLibraryRepository: MusicLibraryRepository

    @Mock
    private lateinit var mockCD : CD
    @Mock
    private lateinit var mockListMediaItem : List<MediaBrowserCompat.MediaItem>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = GetMediaItemsUseCase(
                cdRepository,
                musicLibraryRepository
        )
    }

    @Test
    fun execute_should_returnListMediaItems() {
        `when`(cdRepository.getCD()).thenReturn(Observable.just(mockCD))
        `when`(musicLibraryRepository.getMediaItems(mockCD)).thenReturn(mockListMediaItem)

        val testObserver = sut.execute().test()

        testObserver.assertValue { it == mockListMediaItem }
    }
}