package com.keltapps.makrokosmos.dagger.module.song.presentation

import android.support.v4.app.FragmentPagerAdapter
import com.keltapps.makrokosmos.dagger.scope.FragmentScope
import com.keltapps.makrokosmos.song.presentation.view.PageAdapter
import com.keltapps.makrokosmos.song.presentation.view.SongListParentFragment
import dagger.Module
import dagger.Provides

@Module
class SongListParentModule {

    @Provides
    @FragmentScope
    fun providePageAdapter(
            parentFragment: SongListParentFragment
    ): FragmentPagerAdapter {
        return PageAdapter(parentFragment)
    }
}
