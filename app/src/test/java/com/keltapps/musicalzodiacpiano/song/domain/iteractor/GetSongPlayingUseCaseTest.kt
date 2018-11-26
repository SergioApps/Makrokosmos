package com.keltapps.musicalzodiacpiano.song.domain.iteractor

import com.keltapps.musicalzodiacpiano.audio.client.domain.repository.AudioRepository
import com.keltapps.musicalzodiacpiano.song.domain.entity.Song
import com.keltapps.musicalzodiacpiano.song.domain.repository.CDRepository
import io.reactivex.*
import org.junit.*
import org.mockito.*
import org.mockito.Mockito.`when`

class GetSongPlayingUseCaseTest {

    private lateinit var sut: GetSongPlayingUseCase

    @Mock
    private lateinit var audioRepository: AudioRepository
    @Mock
    private lateinit var cdRepository: CDRepository

    @Mock
    private lateinit var mockSong: Song

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = GetSongPlayingUseCase(
                audioRepository,
                cdRepository
        )
    }

    @Test
    fun execute_should_returnSongPlaying() {
        val mediaId = "mediaId"
        `when`(audioRepository.getSongIdPlaying()).thenReturn(Observable.just(mediaId))
        `when`(cdRepository.getSong(mediaId)).thenReturn(Single.just(mockSong))

        val testObserver = sut.execute().test()

        testObserver.assertValue { it == mockSong }
    }
}