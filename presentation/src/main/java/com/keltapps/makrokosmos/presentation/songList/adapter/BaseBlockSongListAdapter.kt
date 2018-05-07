package com.keltapps.makrokosmos.presentation.songList.adapter

import android.arch.lifecycle.LiveData
import android.support.v7.widget.RecyclerView
import com.keltapps.makrokosmos.presentation.base.adapter.BaseAdapter
import com.keltapps.makrokosmos.presentation.songList.model.CDListItem


abstract class BaseBlockSongListAdapter<T> : BaseAdapter<T>() where T : RecyclerView.ViewHolder {

    abstract fun setSource(observableItems: LiveData<List<CDListItem>>)
}