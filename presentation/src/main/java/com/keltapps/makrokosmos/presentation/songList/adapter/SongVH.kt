package com.keltapps.makrokosmos.presentation.songList.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import com.keltapps.makrokosmos.domain.entity.Song
import com.keltapps.makrokosmos.presentation.databinding.*
import com.keltapps.makrokosmos.presentation.songList.viewModel.*

class SongVH(itemView: View, private val viewModel: SongItemViewModel) : RecyclerView.ViewHolder(itemView) {

    private val binding: ItemSongBinding? = DataBindingUtil.bind(itemView)

    init {
        binding?.viewModel = viewModel
    }

    fun setItem(song: Song) {
        viewModel.setSong(song)
    }
}