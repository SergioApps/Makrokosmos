package com.keltapps.makrokosmos.presentation.songList.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import javax.inject.Inject

class PageAdapter @Inject constructor(
        private val activity: AppCompatActivity
) : FragmentPagerAdapter(activity.supportFragmentManager) {

    @Inject
    lateinit var fragment: SongListFragment

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        fragment.arguments = Bundle().apply {
            putInt(SongListFragment.ARG_INDEX, position)
        }
        return fragment
    }

    override fun getPageTitle(position: Int)
            : CharSequence = activity.getString(fragment.getTitleRes(position))
}