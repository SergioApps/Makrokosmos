package com.keltapps.musicalzodiacpiano.song.presentation.list.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
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