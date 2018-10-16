package com.keltapps.makrokosmos.song.presentation.adapter

import android.databinding.DataBindingUtil
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.*
import com.keltapps.makrokosmos.BR
import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.databinding.ItemSongBinding
import com.keltapps.makrokosmos.song.domain.entity.Song
import com.keltapps.makrokosmos.song.presentation.factory.SongItemViewModelFactory
import javax.inject.Inject

class BlockSongListAdapter @Inject constructor(
        private val itemViewModelFactory: SongItemViewModelFactory
) : ListAdapter<Song, BlockSongListAdapter.SongViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_song, parent, false)
        return SongViewHolder(view, itemViewModelFactory)
    }

    override fun onBindViewHolder(viewHolder: SongViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Song>() {
            override fun areItemsTheSame(oldSong: Song, newSong: Song): Boolean {
                return oldSong.title == newSong.title
            }

            override fun areContentsTheSame(oldSong: Song, newSong: Song): Boolean {
                return oldSong == newSong
            }
        }
    }

    class SongViewHolder(
            private val view: View,
            private val itemViewModelFactory: SongItemViewModelFactory
    ) : RecyclerView.ViewHolder(view) {

        fun bind(song: Song) {
            val binding: ItemSongBinding? = DataBindingUtil.bind(view)
            val itemViewModel = itemViewModelFactory.createViewModel()
            binding?.setVariable(BR.viewModel, itemViewModel)
            itemViewModel.setSong(song)
            binding?.textView?.isSelected = true
        }
    }
}