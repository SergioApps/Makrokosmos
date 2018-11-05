package com.keltapps.makrokosmos.dagger.module.song.presentation

import androidx.fragment.app.FragmentPagerAdapter
import com.keltapps.makrokosmos.dagger.scope.FragmentScope
import com.keltapps.makrokosmos.song.presentation.list.view.*
import dagger.*

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
