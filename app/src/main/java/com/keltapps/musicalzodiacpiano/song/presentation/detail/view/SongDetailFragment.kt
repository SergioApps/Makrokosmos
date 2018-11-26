package com.keltapps.musicalzodiacpiano.song.presentation.detail.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.keltapps.musicalzodiacpiano.R
import com.keltapps.musicalzodiacpiano.databinding.FragmentSongDetailBinding
import com.keltapps.musicalzodiacpiano.song.presentation.detail.annotation.ProvideSongId
import com.keltapps.musicalzodiacpiano.song.presentation.detail.viewmodel.SongDetailViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SongDetailFragment : DaggerFragment() {

    @Inject
    internal lateinit var viewModel: SongDetailViewModel

    private lateinit var binding: FragmentSongDetailBinding

    @ProvideSongId
    fun getSongId(): String = SongDetailFragmentArgs.fromBundle(arguments).songId

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

    override fun onDestroyView() {
        binding.toolbar.setNavigationOnClickListener(null)
        (activity as AppCompatActivity).setSupportActionBar(null)
        super.onDestroyView()
    }
}