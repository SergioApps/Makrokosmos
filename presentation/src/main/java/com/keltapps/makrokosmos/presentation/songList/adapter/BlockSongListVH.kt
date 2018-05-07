package com.keltapps.makrokosmos.presentation.songList.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import com.keltapps.makrokosmos.domain.entity.Song
import com.keltapps.makrokosmos.presentation.databinding.ItemBlockTitleBinding
import com.keltapps.makrokosmos.presentation.databinding.ItemSongBinding
import com.keltapps.makrokosmos.presentation.songList.viewModel.BlockTitleItemViewModel
import com.keltapps.makrokosmos.presentation.songList.viewModel.SongItemViewModel

sealed class BlockSongListVH (itemView: View) : RecyclerView.ViewHolder(itemView)


class BlockTitleVH(itemView: View, private val viewModel: BlockTitleItemViewModel) : BlockSongListVH(itemView) {

    private val binding: ItemBlockTitleBinding? = DataBindingUtil.bind(itemView)

    init {
        binding?.viewModel = viewModel
    }

    fun setItem(title: String) {
        viewModel.setTitle(title)
    }
}

class SongVH(itemView: View, private val viewModel: SongItemViewModel) : BlockSongListVH(itemView) {

    private val binding: ItemSongBinding? = DataBindingUtil.bind(itemView)

    init {
        binding?.viewModel = viewModel
    }

    fun setItem(song: Song) {
        viewModel.setSong(song)
    }
}