package com.keltapps.makrokosmos.presentation.songList.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.keltapps.makrokosmos.presentation.R
import com.keltapps.makrokosmos.presentation.databinding.ActivitySongListBinding
import com.keltapps.makrokosmos.presentation.databinding.FragmentSongListBinding
import com.keltapps.makrokosmos.presentation.songList.adapter.BaseBlockSongListAdapter
import com.keltapps.makrokosmos.presentation.songList.viewModel.MakrokosmosSongListViewModel
import com.keltapps.makrokosmos.presentation.songList.viewModel.SongListViewModel
import javax.inject.Inject

class SongListFragment : Fragment() {

    companion object {
        const val ARG_INDEX = "argIndex"
    }

    @Inject
    lateinit var viewModel: SongListViewModel
    @Inject
    lateinit var adapter: BaseBlockSongListAdapter<*>

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding: FragmentSongListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_song_list, null, false)
        binding.viewModel = viewModel
        viewModel.initialize(0)

        arguments?.takeIf { it.containsKey(ARG_INDEX) }?.apply {
            viewModel.initialize(getInt(ARG_INDEX))
        }
        setUpRecyclerView(binding)
        return binding.root
    }

    fun getTitleRes(position: Int): Int = viewModel.getTitleRes(position)

    private fun setUpRecyclerView(binding: FragmentSongListBinding) {
   /*     binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
        adapter.setSource(viewModel.cdListItems)*/
    }
}