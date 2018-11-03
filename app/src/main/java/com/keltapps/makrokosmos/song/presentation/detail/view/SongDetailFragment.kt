package com.keltapps.makrokosmos.song.presentation.detail.view

import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import android.graphics.drawable.Animatable
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
        /*viewModel.playOrPauseIcon.observe(this, Observer {
            binding.playOrPause.setImageDrawable(it)
            (binding.playOrPause.drawable as? Animatable)?.start()
        })*/
        setupActionBar()
        return binding.root
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