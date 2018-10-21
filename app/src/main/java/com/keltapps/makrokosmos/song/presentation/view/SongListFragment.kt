package com.keltapps.makrokosmos.song.presentation.view

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.databinding.FragmentSongListBinding
import com.keltapps.makrokosmos.song.domain.entity.Song
import com.keltapps.makrokosmos.song.presentation.adapter.BlockSongListAdapter
import com.keltapps.makrokosmos.song.presentation.viewmodel.SongListViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SongListFragment : DaggerFragment() {

    companion object {
        const val ARG_INDEX = "argIndex"
    }

    @Inject
    internal lateinit var viewModel: SongListViewModel
    @Inject
    internal lateinit var adapter: ListAdapter<Song, BlockSongListAdapter.SongViewHolder>

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding: FragmentSongListBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_song_list,
                container,
                false
        )
        binding.viewModel = viewModel
        viewModel.initialize(0)

        arguments?.takeIf { it.containsKey(ARG_INDEX) }?.apply {
            viewModel.initialize(getInt(ARG_INDEX))
        }
        setUpRecyclerView(binding)
        return binding.root
    }

    fun getTitleRes(position: Int): Int {
        return if (position == 0) {
            R.string.tab_name_volume_1
        } else {
            R.string.tab_name_volume_2
        }
    }

    private fun setUpRecyclerView(binding: FragmentSongListBinding) {
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.adapter = adapter
        viewModel.cdListItems.observe(this, Observer { adapter.submitList(it) })
    }
}