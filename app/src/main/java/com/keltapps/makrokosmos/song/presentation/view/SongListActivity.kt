package com.keltapps.makrokosmos.song.presentation.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.FragmentPagerAdapter
import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.databinding.ActivitySongListBinding
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_song_list.*
import javax.inject.Inject

class SongListActivity : DaggerAppCompatActivity() {

    @Inject
    internal lateinit var pageAdapter: FragmentPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivitySongListBinding>(this, R.layout.activity_song_list)
        pager.adapter = pageAdapter
        tabLayout.setupWithViewPager(pager)
        setupActionBar()
        //    window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.title_songListActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
