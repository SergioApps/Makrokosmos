package com.keltapps.makrokosmos.base.presentation.view

import android.animation.*
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView

@BindingAdapter("playPauseAnimation")
fun setPlayPauseAnimation(view: LottieAnimationView, isPlaying: Boolean) {
    view.removeAllAnimatorListeners()
    when {
        isPlaying && view.isAnimating -> setPlayingAnimation(view)
        isPlaying && !view.isAnimating -> handlePauseToPlay(view)
        !isPlaying && view.isAnimating -> setPlayToPauseAnimation(view)
    }
}

private fun handlePauseToPlay(view: LottieAnimationView) {
    setPauseToPlayAnimation(view)
    view.addAnimatorListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {}
        override fun onAnimationEnd(animation: Animator?) {
            setPlayingAnimation(view)
        }

        override fun onAnimationCancel(animation: Animator?) {}
        override fun onAnimationStart(animation: Animator?) {}
    })
}

private fun setPauseToPlayAnimation(view: LottieAnimationView) {
    view.setMinAndMaxProgress(0.285f, 0.857f)
    view.repeatCount = 0
    view.playAnimation()
}

private fun setPlayingAnimation(view: LottieAnimationView) {
    view.setMinAndMaxProgress(0.4345f, 0.857f)
    view.repeatCount = ValueAnimator.INFINITE
    view.playAnimation()
}

private fun setPlayToPauseAnimation(view: LottieAnimationView) {
    view.setMinAndMaxProgress(0.857f, 1f)
    view.repeatCount = 0
    view.playAnimation()
}