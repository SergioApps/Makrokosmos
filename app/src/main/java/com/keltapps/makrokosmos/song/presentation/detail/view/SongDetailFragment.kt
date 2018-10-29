package com.keltapps.makrokosmos.song.presentation.detail.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        viewModel.initialize(
                SongDetailFragmentArgs.fromBundle(arguments).songId
        )
        setupActionBar()
        return binding.root
    }

    private fun setupActionBar() {

        /*   with(activity as AppCompatActivity) {
               setSupportActionBar(binding.toolbar)
               supportActionBar?.setDisplayHomeAsUpEnabled(true)
           }
           binding.toolbar.setNavigationOnClickListener { binding.root.findNavController().navigateUp() }
     */
    }
}