package com.keltapps.makrokosmos.dagger.module.menu.presentation

import android.arch.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.keltapps.makrokosmos.base.presentation.view.createFactory
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
            viewModel: MakrokosmosMenuViewModel
    ): MenuViewModel {
        val viewModelFactory = viewModel.createFactory()
        return ViewModelProviders.of(fragment, viewModelFactory).get(viewModel.javaClass)
    }

    @Provides
    @FragmentScope
    fun provideNavigator(navigator: MakrokosmosNavigator): Navigator {
        return navigator
    }
}