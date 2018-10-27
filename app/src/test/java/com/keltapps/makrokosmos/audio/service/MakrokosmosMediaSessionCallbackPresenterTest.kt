package com.keltapps.makrokosmos.audio.service

import android.content.Context
import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.session.MediaSessionCompat
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class MakrokosmosMediaSessionCallbackPresenterTest {

    private lateinit var sut: MakrokosmosMediaSessionCallbackPresenter

    @Mock
    private lateinit var mediaSessionCompatHelper: MediaSessionCompatHelper

    @Mock
    private lateinit var mediaSessionCallback: MediaSessionCallback
    @Mock
    private lateinit var mockDescription: MediaDescriptionCompat
    @Mock
    private lateinit var mockQueueItem: MediaSessionCompat.QueueItem
    @Mock
    private lateinit var mockQueueItem2: MediaSessionCompat.QueueItem

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = MakrokosmosMediaSessionCallbackPresenter(
                mediaSessionCompatHelper
        )
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
    fun onPrepare_should_doNothing_when_listIsEmpty() {
        sut.onPrepare()

        verifyZeroInteractions(mediaSessionCallback)
    }

}