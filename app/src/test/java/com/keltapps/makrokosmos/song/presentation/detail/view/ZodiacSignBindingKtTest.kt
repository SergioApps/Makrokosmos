package com.keltapps.makrokosmos.song.presentation.detail.view

import com.airbnb.lottie.LottieAnimationView
import org.junit.*
import org.mockito.*
import org.mockito.Mockito.verify

class ZodiacSignBindingKtTest {

    @Mock
    private lateinit var view: LottieAnimationView

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun setZodiacSign_should_playAnimation_when_isPlayingIsTrue() {
        setZodiacSign(view, true, 0)

        verify(view).playAnimation()
    }

    @Test
    fun setZodiacSign_should_pauseAnimation_when_isPlayingIsFalse() {
        setZodiacSign(view, false, 0)

        verify(view).pauseAnimation()
    }
}