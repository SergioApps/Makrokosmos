package com.keltapps.makrokosmos.song.presentation.detail.view

import android.animation.Animator
import android.animation.ValueAnimator
import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.databinding.FragmentSongDetailBinding
import com.keltapps.makrokosmos.song.presentation.detail.viewmodel.SongDetailViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_song_detail.*
import javax.inject.Inject

class SongDetailFragment : DaggerFragment() {

    @Inject
    internal lateinit var viewModel: SongDetailViewModel

    private lateinit var binding: FragmentSongDetailBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_song_detail,
                container,
                false
        )
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        viewModel.initialize(
                SongDetailFragmentArgs.fromBundle(arguments).songId
        )
        handlePlayAnimation()
        setupActionBar()
        return binding.root
    }

    private fun handlePlayAnimation() {
        viewModel.isPlaying.observe(this, Observer { isPlaying ->
            playOrPause.removeAllAnimatorListeners()
            if (isPlaying) {
                if (!playOrPause.isAnimating) {
                    setPauseToPlayAnimation()
                    playOrPause.addAnimatorListener(object : Animator.AnimatorListener {
                        override fun onAnimationRepeat(animation: Animator?) {}
                        override fun onAnimationEnd(animation: Animator?) {
                            setPlayingAnimation()
                        }

                        override fun onAnimationCancel(animation: Animator?) {}
                        override fun onAnimationStart(animation: Animator?) {}
                    })
                } else {
                    setPlayingAnimation()
                }
            } else {
                setPlayToPauseAnimation()
            }
        })
    }

    private fun setPauseToPlayAnimation() {
        playOrPause.setMinAndMaxProgress(0.285f, 0.857f)
        playOrPause.repeatCount = 0
        playOrPause.playAnimation()
    }

    private fun setPlayingAnimation() {
        playOrPause.removeAllAnimatorListeners()
        playOrPause.setMinAndMaxProgress(0.4345f, 0.857f)
        playOrPause.repeatCount = ValueAnimator.INFINITE
        playOrPause.playAnimation()
    }

    private fun setPlayToPauseAnimation() {
        playOrPause.removeAllAnimatorListeners()
        playOrPause.setMinAndMaxProgress(0.857f, 1f)
        playOrPause.repeatCount = 0
        playOrPause.playAnimation()
    }

    private fun setupActionBar() {
        with(activity as AppCompatActivity) {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = ""
        }
        binding.toolbar.setNavigationOnClickListener { binding.root.findNavController().navigateUp() }

    }
}