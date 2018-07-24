package com.keltapps.makrokosmos.presentation.songList.adapter

import android.arch.lifecycle.*
import android.view.*
import com.keltapps.makrokosmos.domain.entity.Song
import com.keltapps.makrokosmos.presentation.R
import com.keltapps.makrokosmos.presentation.songList.viewModel.MakrokosmosSongItemViewModel
import javax.inject.Inject

class BlockSongListAdapter @Inject constructor(
        private val lifecycleOwner: LifecycleOwner
) : BaseBlockSongListAdapter<SongVH>() {

    private val items = ArrayList<Song>()

    private lateinit var itemsObservable: LiveData<List<Song>>

    private val callback = Observer<List<Song>> {
        items.clear()
        items.addAll(itemsObservable.value ?: ArrayList())
        notifyDataSetChanged()
    }

    override fun setSource(observableItems: LiveData<List<Song>>) {
        itemsObservable = observableItems
        itemsObservable.observe(lifecycleOwner, callback)
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongVH {
        return SongVH(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_song, parent, false), MakrokosmosSongItemViewModel())
    }

    override fun onBindViewHolder(holder: SongVH, position: Int) {
        holder.setItem(items[position])
    }
}