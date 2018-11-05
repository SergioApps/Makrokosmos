package com.keltapps.makrokosmos.base.presentation.view

import android.animation.ValueAnimator
import com.airbnb.lottie.LottieAnimationView
import org.junit.*
import org.mockito.*
import org.mockito.Mockito.*

class PlayPauseAnimationBindingsKtTest {

    @Mock
    private lateinit var view: LottieAnimationView

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun setPlayPauseAnimation_should_setPlayingAnimation_when_isPlayingAndIsAnimatingAreTrue() {
        `when`(view.isAnimating).thenReturn(true)

        setPlayPauseAnimation(view, true)

        verify(view).removeAllAnimatorListeners()
        verify(view).setMinAndMaxProgress(0.4345f, 0.857f)
        verify(view).repeatCount = ValueAnimator.INFINITE
        verify(view).playAnimation()
    }

    @Test
    fun setPlayPauseAnimation_should_setPauseToPlayingAnimation_when_isPlayingIsTrueAndIsAnimatingIsFalse() {
        `when`(view.isAnimating).thenReturn(false)

        setPlayPauseAnimation(view, true)

        verify(view).removeAllAnimatorListeners()
        verify(view).setMinAndMaxProgress(0.285f, 0.857f)
        verify(view).repeatCount = 0
        verify(view).playAnimation()
    }

    @Test
    fun setPlayPauseAnimation_should_setPlayingToPauseAnimation_when_isPlayingIsFalseAndIsAnimatingIsTrue() {
        `when`(view.isAnimating).thenReturn(true)

        setPlayPauseAnimation(view, false)

        verify(view).removeAllAnimatorListeners()
        verify(view).setMinAndMaxProgress(0.857f, 1f)
        verify(view).repeatCount = 0
        verify(view).playAnimation()
    }

    @Test
    fun setPlayPauseAnimation_should_doNothing_when_isPlayingAndIsAnimatingAreFalse() {
        `when`(view.isAnimating).thenReturn(false)

        setPlayPauseAnimation(view, false)

        verify(view).removeAllAnimatorListeners()
        verify(view, never()).setMinAndMaxProgress(anyFloat(), anyFloat())
        verify(view, never()).repeatCount = anyInt()
        verify(view, never()).playAnimation()
    }
}