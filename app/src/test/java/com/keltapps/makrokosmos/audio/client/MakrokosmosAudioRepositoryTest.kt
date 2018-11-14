package com.keltapps.makrokosmos.audio.client

import android.support.v4.media.*
import android.support.v4.media.session.MediaControllerCompat
import com.google.common.truth.Truth.assertThat
import com.keltapps.makrokosmos.audio.client.data.*
import com.keltapps.makrokosmos.audio.client.domain.entity.PlayingState
import com.keltapps.makrokosmos.song.domain.entity.Song
import io.reactivex.Observable
import org.junit.*
import org.mockito.*
import org.mockito.Mockito.*

class MakrokosmosAudioRepositoryTest {

    private companion object {
        const val MEDIA_ID = "mediaId"
    }

    private lateinit var sut: MakrokosmosAudioRepository

    @Mock
    private lateinit var mediaBrowserHelper: MediaBrowserHelper
    @Mock
    private lateinit var playingStateMapper: PlayingStateMapper

    @Mock
    private lateinit var mockTransportControls: MediaControllerCompat.TransportControls
    @Mock
    private lateinit var mockSong: Song
    @Mock
    private lateinit var mockMediaMetadataCompat: MediaMetadataCompat
    @Mock
    private lateinit var mockDescription: MediaDescriptionCompat

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = MakrokosmosAudioRepository(
                mediaBrowserHelper,
                playingStateMapper
        )
        `when`(mediaBrowserHelper.getTransportControls()).thenReturn(mockTransportControls)
        `when`(mockMediaMetadataCompat.description).thenReturn(mockDescription)
        `when`(mockDescription.mediaId).thenReturn(MEDIA_ID)
    }

    @Test
    fun start_should_callOnStart() {
        sut.start()

        verify(mediaBrowserHelper).onStart()
    }

    @Test
    fun play_should_callOnPlay() {
        `when`(mockSong.id).thenReturn("id")

        sut.play(mockSong.id)

        verify(mediaBrowserHelper).onPlay(mockSong.id)
    }

    @Test
    fun continuePlaying_should_callPlay() {
        sut.continuePlaying()

        verify(mockTransportControls).play()
    }

    @Test
    fun pause_should_callPause() {
        sut.pause()

        verify(mockTransportControls).pause()
    }

    @Test
    fun stop_should_callOnStop() {
        sut.stop()

        verify(mediaBrowserHelper).onStop()
    }

    @Test
    fun skipToNext_should_callSkipToNext() {
        sut.skipToNext()

        verify(mockTransportControls).skipToNext()
    }

    @Test
    fun skipToPrevious_should_callSkipToPrevious() {
        sut.skipToPrevious()

        verify(mockTransportControls).skipToPrevious()
    }

    @Test
    fun seekTo_should_callSeekTo() {
        val position = 10L

        sut.seekTo(position)

        verify(mockTransportControls).seekTo(position)
    }

    @Test
    fun getSongIdPlaying_should_returnObservableWithMediaId() {
        `when`(mediaBrowserHelper.onMetadataChanged).thenReturn(Observable.just(mockMediaMetadataCompat))

        val testObserver = sut.getSongIdPlaying().test()

        testObserver.assertValue { it == MEDIA_ID }
    }

    @Test
    fun getPlayingState_should_returnPlayingState() {
        val playingState = 1
        `when`(mediaBrowserHelper.onStateChanged).thenReturn(Observable.just(playingState))
        `when`(playingStateMapper.map(playingState)).thenReturn(PlayingState.Playing)

        val testObservable = sut.getPlayingState().test()

        testObservable.assertValue { it is PlayingState.Playing }
    }

    @Test
    fun getCurrentPlayingState_should_returnPlayingState() {
        val playingState = 1
        `when`(mediaBrowserHelper.getCurrentState()).thenReturn(playingState)
        `when`(playingStateMapper.map(playingState)).thenReturn(PlayingState.Playing)

        val result = sut.getCurrentPlayingState()

        assert(result is PlayingState.Playing)
    }

    @Test
    fun getCurrentPositionInSeconds_should_returnCurrentPosition() {
        val position = 1000L
        `when`(mediaBrowserHelper.getCurrentPosition()).thenReturn(position)

        val result = sut.getCurrentPositionInSeconds()

        assertThat(result).isEqualTo(1)
    }
}