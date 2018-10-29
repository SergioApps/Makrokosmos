package com.keltapps.makrokosmos.audio.service.data.sessioncallback

import android.support.v4.media.*
import android.support.v4.media.session.MediaSessionCompat
import com.google.common.truth.Truth.assertThat
import com.keltapps.makrokosmos.audio.service.domain.repository.MusicLibraryRepository
import org.junit.*
import org.mockito.*
import org.mockito.Mockito.*

class MakrokosmosMediaSessionCallbackPresenterTest {

    private companion object {
        const val MEDIA_ID = "mediaId"
    }

    private lateinit var sut: MakrokosmosMediaSessionCallbackPresenter

    @Mock
    private lateinit var mediaSessionCompatHelper: MediaSessionCompatHelper
    @Mock
    private lateinit var musicLibraryRepository: MusicLibraryRepository

    @Mock
    private lateinit var mediaSessionCallback: MediaSessionCallback
    @Mock
    private lateinit var mockDescription: MediaDescriptionCompat
    @Mock
    private lateinit var mockDescription2: MediaDescriptionCompat
    @Mock
    private lateinit var mockQueueItem: MediaSessionCompat.QueueItem
    @Mock
    private lateinit var mockQueueItem2: MediaSessionCompat.QueueItem
    @Mock
    private lateinit var mockMediaMetadataCompat: MediaMetadataCompat
    @Mock
    private lateinit var mockMediaMetadataCompat2: MediaMetadataCompat
    @Mock
    private lateinit var mockMediaDescriptionCompat: MediaDescriptionCompat
    @Mock
    private lateinit var mockMediaDescriptionCompat2: MediaDescriptionCompat

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = spy(MakrokosmosMediaSessionCallbackPresenter(
                mediaSessionCompatHelper,
                musicLibraryRepository
        ))
        sut.attach(mediaSessionCallback)
        `when`(mediaSessionCompatHelper.getQueueItem(mockDescription)).thenReturn(mockQueueItem)
    }

    @Test
    fun onAddQueueItem_should_callSetQueueWithNewItem() {

        sut.onAddQueueItem(mockDescription)

        verify(mediaSessionCallback).setQueue(arrayListOf(mockQueueItem))
        assertThat(sut.queueIndex).isEqualTo(0)
    }

    @Test
    fun onAddQueueItem_should_callSetQueueWithTwoItem_when_callTwice() {
        sut.onAddQueueItem(mockDescription)

        `when`(mediaSessionCompatHelper.getQueueItem(mockDescription)).thenReturn(mockQueueItem2)
        sut.onAddQueueItem(mockDescription)

        verify(mediaSessionCallback, times(2))
                .setQueue(arrayListOf(mockQueueItem, mockQueueItem2))
        assertThat(sut.queueIndex).isEqualTo(0)
    }

    @Test
    fun onRemoveQueueItem_should_callSetQueueWithEmptyList_when_removeLastItem() {
        sut.onAddQueueItem(mockDescription)

        sut.onRemoveQueueItem(mockDescription)

        verify(mediaSessionCallback, times(2)).setQueue(emptyList())
    }

    @Test
    fun onRemoveQueueItem_should_calWithCurrentList_when_itemNotFound() {
        sut.onAddQueueItem(mockDescription)

        sut.onRemoveQueueItem(mock(MediaDescriptionCompat::class.java))

        verify(mediaSessionCallback, times(2)).setQueue(arrayListOf(mockQueueItem))
    }

    @Test
    fun onRemoveQueueItem_should_callSetQueueWith1LessItem_when_itemFound() {
        sut.onAddQueueItem(mockDescription)
        sut.onAddQueueItem(mockDescription)

        sut.onRemoveQueueItem(mockDescription)

        verify(mediaSessionCallback, times(3)).setQueue(arrayListOf(mockQueueItem))
    }

    @Test
    fun onPrepareWithMediaId_should_moveQueueIndex() {
        `when`(mockQueueItem.description).thenReturn(mockMediaDescriptionCompat)
        `when`(mockMediaDescriptionCompat.mediaId).thenReturn(null)
        sut.onAddQueueItem(mockDescription)
        `when`(mediaSessionCompatHelper.getQueueItem(mockDescription2)).thenReturn(mockQueueItem2)
        `when`(mockQueueItem2.description).thenReturn(mockMediaDescriptionCompat2)
        `when`(mockMediaDescriptionCompat2.mediaId).thenReturn(MEDIA_ID)
        sut.onAddQueueItem(mockDescription2)

        sut.onPrepare(MEDIA_ID)

        assertThat(sut.queueIndex).isEqualTo(1)
    }

    @Test
    fun onPrepare_should_doNothing_when_listIsEmpty() {
        sut.onPrepare()

        verifyZeroInteractions(mediaSessionCallback)
    }

    @Test
    fun onPrepare_should_doNothing_when_mediaIdIsNull() {
        `when`(mockQueueItem.description).thenReturn(mockMediaDescriptionCompat)
        `when`(mockMediaDescriptionCompat.mediaId).thenReturn(null)
        sut.onAddQueueItem(mockDescription)

        sut.onPrepare()

        verify(mediaSessionCallback, never()).prepareMedia(mockMediaMetadataCompat)
    }

    @Test
    fun onPrepare_should_callPrepareMedia_when_listIsNotEmpty() {
        mockOnPrepare()
        sut.onAddQueueItem(mockDescription)

        sut.onPrepare()

        verify(mediaSessionCallback).prepareMedia(mockMediaMetadataCompat)
    }

    private fun mockOnPrepare() {
        `when`(mockQueueItem.description).thenReturn(mockMediaDescriptionCompat)
        `when`(mockMediaDescriptionCompat.mediaId).thenReturn(MEDIA_ID)
        `when`(musicLibraryRepository.getMetadata(MEDIA_ID)).thenReturn(mockMediaMetadataCompat)
    }

    @Test
    fun onPlay_should_doNothing_when_playlistIsEmpty() {
        sut.onPlay()

        verifyZeroInteractions(mediaSessionCallback)
    }

    @Test
    fun onPlay_should_callOnPrepare_when_onPrepareWasNeverCalled() {
        mockOnPrepare()
        sut.onAddQueueItem(mockDescription)

        sut.onPlay()

        verify(sut).onPrepare()
        verify(mediaSessionCallback).play(mockMediaMetadataCompat)
    }

    @Test
    fun onPlay_should_notCallOnPrepare_when_onPrepareWasAlreadyCalled() {
        mockOnPrepare()
        sut.onAddQueueItem(mockDescription)
        sut.onPrepare()

        sut.onPlay()

        verify(sut).onPrepare()
        verify(mediaSessionCallback).play(mockMediaMetadataCompat)
    }

    @Test
    fun onSkipToNext_should_playNextSong() {
        mockOnPrepare()
        sut.onAddQueueItem(mockDescription)
        `when`(musicLibraryRepository.getMetadata(MEDIA_ID)).thenReturn(mockMediaMetadataCompat2)
        sut.onAddQueueItem(mockDescription)
        sut.onPrepare()

        sut.onSkipToNext()

        verify(sut).onPlay()
        verify(mediaSessionCallback).play(mockMediaMetadataCompat2)
    }

    @Test
    fun onSkipToPrevious_should_playPreviousSong() {
        mockOnPrepare()
        sut.onAddQueueItem(mockDescription)
        sut.onAddQueueItem(mockDescription)
        `when`(musicLibraryRepository.getMetadata(MEDIA_ID)).thenReturn(mockMediaMetadataCompat2)
        sut.onAddQueueItem(mockDescription)
        sut.onPrepare()

        sut.onSkipToPrevious()

        verify(sut).onPlay()
        verify(mediaSessionCallback).play(mockMediaMetadataCompat2)
    }
}