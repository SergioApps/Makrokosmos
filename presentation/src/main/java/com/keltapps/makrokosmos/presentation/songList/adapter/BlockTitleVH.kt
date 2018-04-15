package com.keltapps.makrokosmos.presentation.songList.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import com.keltapps.makrokosmos.presentation.databinding.ItemBlockTitleBinding
import com.keltapps.makrokosmos.presentation.songList.viewModel.BlockTitleItemViewModel

class BlockTitleVH(itemView: View, private val viewModel: BlockTitleItemViewModel) : RecyclerView.ViewHolder(itemView) {

    private val binding: ItemBlockTitleBinding? = DataBindingUtil.bind(itemView)

    init {
        binding?.viewModel = viewModel
    }

    fun setItem(title: String) {
        viewModel.setTitle(title)
    }
}