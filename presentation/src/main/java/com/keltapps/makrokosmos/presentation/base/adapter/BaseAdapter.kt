package com.keltapps.makrokosmos.presentation.base.adapter

import android.support.v7.widget.RecyclerView

abstract class BaseAdapter<T> : RecyclerView.Adapter<T>() where T : RecyclerView.ViewHolder