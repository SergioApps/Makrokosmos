package com.keltapps.makrokosmos.dagger.module.menu.presentation

import androidx.lifecycle.ViewModelProviders
import com.keltapps.makrokosmos.base.presentation.view.createFactory
import com.keltapps.makrokosmos.dagger.scope.FragmentScope
import com.keltapps.makrokosmos.menu.presentation.view.MenuFragment
import com.keltapps.makrokosmos.menu.presentation.viewmodel.*
import dagger.*

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
}