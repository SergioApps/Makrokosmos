package com.keltapps.musicalzodiacpiano.dagger.module.song.presentation

import androidx.fragment.app.FragmentPagerAdapter
import com.keltapps.musicalzodiacpiano.dagger.scope.FragmentScope
import com.keltapps.musicalzodiacpiano.song.presentation.list.view.*
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
