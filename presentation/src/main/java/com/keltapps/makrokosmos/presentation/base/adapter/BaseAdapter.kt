package com.keltapps.makrokosmos.presentation.base.adapter

import android.support.v7.widget.RecyclerView

abstract class BaseAdapter<T, in I> : RecyclerView.Adapter<T>() where T : RecyclerView.ViewHolder {

    abstract fun setSource(observableItems: I)

    abstract fun cleanup()
}