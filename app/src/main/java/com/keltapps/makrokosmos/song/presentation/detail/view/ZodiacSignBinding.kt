package com.keltapps.makrokosmos.song.presentation.detail.view

import androidx.databinding.BindingAdapter
import com.airbnb.lottie.*
import com.airbnb.lottie.model.KeyPath


@BindingAdapter("isPlaying", "color")
fun setZodiacSign(view: LottieAnimationView, isPlaying: Boolean, color: Int) {
    handleRippleAnimation(view, isPlaying)
    setupZodiacSignColor(view, color)
}


private fun handleRippleAnimation(view: LottieAnimationView, isPlaying: Boolean) {
    if (isPlaying) {
        view.playAnimation()
    } else {
        view.pauseAnimation()
    }
}

private fun setupZodiacSignColor(view: LottieAnimationView, color: Int) {
    view.addValueCallback(
            KeyPath("**"),
            LottieProperty.COLOR)
    { color }
}
