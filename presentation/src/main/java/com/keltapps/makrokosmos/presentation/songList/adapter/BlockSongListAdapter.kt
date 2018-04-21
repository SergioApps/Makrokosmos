package com.keltapps.makrokosmos.presentation.songList.adapter

import android.databinding.Observable
import android.databinding.ObservableField
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.keltapps.makrokosmos.domain.entity.Song
import com.keltapps.makrokosmos.presentation.R

import com.keltapps.makrokosmos.presentation.base.adapter.BaseAdapter
import com.keltapps.makrokosmos.presentation.songList.model.CDListItem
import com.keltapps.makrokosmos.presentation.songList.model.SongListItem
import com.keltapps.makrokosmos.presentation.songList.model.TitleListItem
import com.keltapps.makrokosmos.presentation.songList.viewModel.MakrokosmosBlockTitleItemViewModel
import com.keltapps.makrokosmos.presentation.songList.viewModel.MakrokosmosSongItemViewModel
import javax.inject.Inject

class BlockSongListAdapter @Inject constructor()
    : BaseAdapter<RecyclerView.ViewHolder, ObservableField<List<CDListItem>>>() {

    private val items = ArrayList<CDListItem>()

    private var itemsObservable: ObservableField<List<CDListItem>>? = null

    private val callback = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            items.clear()
            items.addAll(itemsObservable?.get() ?: ArrayList())
            notifyDataSetChanged()
        }
    }

    override fun setSource(observableItems: ObservableField<List<CDListItem>>) {
        cleanup()
        itemsObservable = observableItems
        itemsObservable?.addOnPropertyChangedCallback(callback)
    }

    override fun cleanup() {
        itemsObservable?.removeOnPropertyChangedCallback(callback)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return items[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            CDListItem.TYPE_TITLE -> BlockTitleVH(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_block_title, parent, false), MakrokosmosBlockTitleItemViewModel())
            CDListItem.TYPE_SONG -> SongVH(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_song, parent, false), MakrokosmosSongItemViewModel())
            else -> {
                throw NotImplementedError("type not support it")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            CDListItem.TYPE_TITLE -> (holder as BlockTitleVH).setItem((items[position] as TitleListItem).title)
            CDListItem.TYPE_SONG -> (holder as SongVH).setItem((items[position] as SongListItem).song)
        }
    }
}