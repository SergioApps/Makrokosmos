package com.keltapps.makrokosmos.song.presentation.list.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.findNavController
import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.databinding.FragmentSongListParentBinding
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_info.*
import kotlinx.android.synthetic.main.fragment_song_list_parent.*
import javax.inject.Inject

class SongListParentFragment : DaggerFragment() {

    private companion object {
        const val SELECTED_TAB = "selectedTab"
        const val STATE_SCROLL = "stateScroll"
    }

    @Inject
    internal lateinit var pageAdapter: FragmentPagerAdapter

    private lateinit var binding: FragmentSongListParentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentSongListParentBinding>(
                inflater,
                R.layout.fragment_song_list_parent,
                null,
                false
        ).apply {
            pager.adapter = pageAdapter
            tabLayout.setupWithViewPager(pager)
        }
        setupActionBar(binding.root)
        return binding.root
    }

    private fun setupActionBar(root: View) {
        with(activity as AppCompatActivity) {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.title = getString(R.string.title_songListActivity)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbar.setNavigationOnClickListener { root.findNavController().navigateUp() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tabLayout.getTabAt(savedInstanceState?.getInt(SELECTED_TAB) ?: 0)?.select()
        binding.coordinatorLayout.scrollY = savedInstanceState?.getInt(STATE_SCROLL) ?: 0
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SELECTED_TAB, binding.tabLayout.selectedTabPosition)
        outState.putInt(STATE_SCROLL, binding.coordinatorLayout.scrollY)
    }

    override fun onDestroyView() {
        binding.apply {
            pager.adapter = null
            toolbar.setNavigationOnClickListener(null)
            tabLayout.setupWithViewPager(null)
        }
        (activity as AppCompatActivity).setSupportActionBar(null)
        super.onDestroyView()
    }
}
