package com.keltapps.makrokosmos.presentation.songList.adapter

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.view.LayoutInflater
import android.view.ViewGroup
import com.keltapps.makrokosmos.presentation.R
import com.keltapps.makrokosmos.presentation.songList.model.CDListItem
import com.keltapps.makrokosmos.presentation.songList.model.SongListItem
import com.keltapps.makrokosmos.presentation.songList.model.TitleListItem
import com.keltapps.makrokosmos.presentation.songList.viewModel.MakrokosmosBlockTitleItemViewModel
import com.keltapps.makrokosmos.presentation.songList.viewModel.MakrokosmosSongItemViewModel
import javax.inject.Inject

class BlockSongListAdapter @Inject constructor(private val lifecycleOwner: LifecycleOwner)
    : BaseBlockSongListAdapter<BlockSongListVH>() {

    companion object {
        private const val typeTitle = 0
        private const val typeSong = 1
    }

    private val items = ArrayList<CDListItem>()

    private lateinit var itemsObservable: LiveData<List<CDListItem>>

    private val callback = Observer<List<CDListItem>> {
        items.clear()
        items.addAll(itemsObservable.value ?: ArrayList())
        notifyDataSetChanged()
    }

    override fun setSource(observableItems: LiveData<List<CDListItem>>) {
        itemsObservable = observableItems
        itemsObservable.observe(lifecycleOwner, callback)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is TitleListItem -> typeTitle
            is SongListItem -> typeSong
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlockSongListVH {
        return when (viewType) {
            typeTitle -> BlockTitleVH(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_block_title, parent, false), MakrokosmosBlockTitleItemViewModel())
            typeSong -> SongVH(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_song, parent, false), MakrokosmosSongItemViewModel())
            else -> {
                throw NotImplementedError("type not support it")
            }
        }
    }

    override fun onBindViewHolder(holder: BlockSongListVH, position: Int) {
        when (holder) {
            is BlockTitleVH -> holder.setItem((items[position] as TitleListItem).title)
            is SongVH -> holder.setItem((items[position] as SongListItem).song)
        }
    }
}