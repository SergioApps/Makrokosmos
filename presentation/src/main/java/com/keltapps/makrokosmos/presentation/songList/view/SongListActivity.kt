package com.keltapps.makrokosmos.presentation.songList.view

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.keltapps.makrokosmos.presentation.R
import com.keltapps.makrokosmos.presentation.base.view.BaseActivity
import com.keltapps.makrokosmos.presentation.databinding.ActivitySongListBinding
import com.keltapps.makrokosmos.presentation.songList.adapter.BaseBlockSongListAdapter
import com.keltapps.makrokosmos.presentation.songList.viewModel.*
import dagger.android.AndroidInjection
import javax.inject.Inject


class SongListActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: SongListViewModel

    @Inject
    lateinit var adapter: BaseBlockSongListAdapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        ViewModelProviders.of(this).get(MakrokosmosSongListViewModel::class.java)
        val binding = DataBindingUtil.setContentView<ActivitySongListBinding>(this, R.layout.activity_song_list)
        binding.viewModel = viewModel
        viewModel.initialize(0)
        setUpRecyclerView(binding)
    }

    private fun setUpRecyclerView(binding: ActivitySongListBinding) {
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
        adapter.setSource(viewModel.cdListItems)
    }
}
