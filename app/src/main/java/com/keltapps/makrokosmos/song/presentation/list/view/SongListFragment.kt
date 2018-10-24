package com.keltapps.makrokosmos.song.presentation.list.view

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.base.presentation.SingleLiveEvent
import com.keltapps.makrokosmos.databinding.FragmentSongListBinding
import com.keltapps.makrokosmos.navigation.Navigator
import com.keltapps.makrokosmos.song.domain.entity.Song
import com.keltapps.makrokosmos.song.presentation.list.adapter.BlockSongListAdapter
import com.keltapps.makrokosmos.song.presentation.list.viewmodel.SongListViewModel
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
    @Inject
    internal lateinit var navigator: Navigator
    @Inject
    internal lateinit var openSongDetail: SingleLiveEvent<String>

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

        arguments?.takeIf { it.containsKey(ARG_INDEX) }?.apply {
            viewModel.initialize(getInt(ARG_INDEX))
        }
        setUpRecyclerView(binding)
        openSongDetail.observe(this, Observer {
            navigator.openSongDetail(findNavController(), it!!)
        })
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