package com.keltapps.makrokosmos.song.presentation.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.databinding.FragmentSongListParentBinding
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_song_list_parent.*
import javax.inject.Inject

class SongListParentFragment : DaggerFragment() {

    @Inject
    internal lateinit var pageAdapter: FragmentPagerAdapter

    private lateinit var binding: FragmentSongListParentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_song_list_parent,
                null,
                false
        )
        binding.pager.adapter = pageAdapter
        binding.tabLayout.setupWithViewPager(binding.pager)
        setupActionBar()
        return binding.root
    }

    private fun setupActionBar() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_songListActivity)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { binding.root.findNavController().navigateUp() }
    }
}
