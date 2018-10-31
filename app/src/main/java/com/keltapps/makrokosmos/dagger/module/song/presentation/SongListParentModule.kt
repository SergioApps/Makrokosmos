package com.keltapps.makrokosmos.dagger.module.song.presentation

import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.fragment.findNavController
import com.keltapps.makrokosmos.dagger.scope.FragmentScope
import com.keltapps.makrokosmos.menu.presentation.view.MenuFragment
import com.keltapps.makrokosmos.navigation.MakrokosmosNavigator
import com.keltapps.makrokosmos.navigation.Navigator
import com.keltapps.makrokosmos.song.presentation.list.view.PageAdapter
import com.keltapps.makrokosmos.song.presentation.list.view.SongListParentFragment
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

    @Provides
    @FragmentScope
    fun provideNavigator(navigator: MakrokosmosNavigator): Navigator {
        return navigator
    }
}
