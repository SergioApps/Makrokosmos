package com.keltapps.musicalzodiacpiano.song.presentation.list.view

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import com.keltapps.musicalzodiacpiano.R
import com.keltapps.musicalzodiacpiano.base.presentation.SingleLiveEvent
import com.keltapps.musicalzodiacpiano.databinding.FragmentSongListBinding
import com.keltapps.musicalzodiacpiano.navigation.Navigator
import com.keltapps.musicalzodiacpiano.song.domain.entity.Song
import com.keltapps.musicalzodiacpiano.song.presentation.list.adapter.BlockSongListAdapter
import com.keltapps.musicalzodiacpiano.song.presentation.list.annotation.ProvideVolumeIndex
import com.keltapps.musicalzodiacpiano.song.presentation.list.viewmodel.SongListViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_song_list.*
import javax.inject.Inject

class SongListFragment : DaggerFragment() {

    companion object {
        const val ARG_INDEX = "argIndex"
        private const val STATE_SCROLL = "stateScroll"
    }

    @Inject
    internal lateinit var viewModel: SongListViewModel
    @Inject
    internal lateinit var adapter: ListAdapter<Song, BlockSongListAdapter.SongViewHolder>
    @Inject
    internal lateinit var navigator: Navigator
    @Inject
    internal lateinit var openSongDetail: SingleLiveEvent<String>

    @ProvideVolumeIndex
    fun getVolumeIndex(): Int = arguments!!.getInt(ARG_INDEX)

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
        setUpRecyclerView(binding)
        openSongDetail.observe(this, Observer {
            navigator.openSongDetail(findNavController(), it)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.scrollY = savedInstanceState?.getInt(STATE_SCROLL) ?: 0
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        recyclerView?.let { outState.putInt(STATE_SCROLL, it.scrollY) }
    }

    override fun onDestroyView() {
        recyclerView?.adapter = null
        super.onDestroyView()
    }
}