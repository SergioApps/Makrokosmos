package com.keltapps.makrokosmos.dagger.module.song.presentation

import android.support.v4.app.FragmentPagerAdapter
import com.keltapps.makrokosmos.dagger.scope.ActivityScope
import com.keltapps.makrokosmos.song.presentation.view.PageAdapter
import com.keltapps.makrokosmos.song.presentation.view.SongListActivity
import dagger.Module
import dagger.Provides

@Module
class SongListModule {

    @Provides
    @ActivityScope
    fun providePageAdapter(
            activity: SongListActivity
    ): FragmentPagerAdapter {
        return PageAdapter(activity)
    }
}
