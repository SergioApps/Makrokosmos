package com.keltapps.makrokosmos.audio.service.player

import android.content.Context
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class MakrokosmosAudioNoisyReceiverTest {

    private lateinit var sut: MakrokosmosAudioNoisyReceiver

    @Mock
    private lateinit var context: Context

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = MakrokosmosAudioNoisyReceiver(context)
    }

    @Test
    fun register_should_registerReceiver_when_isFirstCall() {

        sut.register()

        verify(context).registerReceiver(any(), any())
    }

    @Test
    fun register_should_callOnlyOnceRegisterReceiver_when_calledTwice() {

        sut.register()
        sut.register()

        verify(context).registerReceiver(any(), any())
    }

    @Test
    fun unregister_should_unregisterReceiver_when_wasRegisterBefore() {
        sut.register()

        sut.unregister()

        verify(context).unregisterReceiver(any())
    }

    @Test
    fun unregister_should_doNothing_when_wasNotRegisterBefore() {
        sut.unregister()

        verifyZeroInteractions(context)
    }
}