package com.keltapps.makrokosmos.song.presentation.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import javax.inject.Inject

class PageAdapter @Inject constructor(
        private val fragment: Fragment
) : FragmentPagerAdapter(fragment.childFragmentManager) {

    private val fragmentArray: Array<SongListFragment> = arrayOf(SongListFragment(), SongListFragment())

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        fragmentArray[position].arguments = Bundle().apply {
            putInt(SongListFragment.ARG_INDEX, position)
        }
        return fragmentArray[position]
    }

    override fun getPageTitle(position: Int): CharSequence {
        return fragment.getString(fragmentArray[position].getTitleRes(position))
    }
}