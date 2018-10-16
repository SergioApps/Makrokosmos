package com.keltapps.makrokosmos.dagger.module.menu.presentation

import android.arch.lifecycle.ViewModelProviders
import com.keltapps.makrokosmos.base.view.createFactory
import com.keltapps.makrokosmos.dagger.scope.ActivityScope
import com.keltapps.makrokosmos.dagger.scope.FragmentScope
import com.keltapps.makrokosmos.menu.presentation.view.MenuFragment
import com.keltapps.makrokosmos.menu.presentation.viewmodel.MenuViewModel
import com.keltapps.makrokosmos.menu.presentation.viewmodel.MakrokosmosMenuViewModel
import com.keltapps.makrokosmos.navigation.MakrokosmosNavigator
import com.keltapps.makrokosmos.navigation.Navigator
import dagger.Module
import dagger.Provides

@Module
class MenuModule {

    @Provides
    @FragmentScope
    fun provideMainViewModel(
            fragment: MenuFragment,
            navigator: Navigator
    ): MenuViewModel {
        val viewModel = MakrokosmosMenuViewModel(navigator)
        val viewModelFactory = viewModel.createFactory()
        return ViewModelProviders.of(fragment, viewModelFactory).get(viewModel.javaClass)
    }

    @Provides
    @FragmentScope
    fun provideNavigator(fragment: MenuFragment): Navigator {
        return MakrokosmosNavigator(fragment)
    }
}