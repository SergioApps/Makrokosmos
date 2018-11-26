package com.keltapps.musicalzodiacpiano.dagger.module.menu.presentation

import androidx.lifecycle.ViewModelProviders
import com.keltapps.musicalzodiacpiano.base.presentation.view.createFactory
import com.keltapps.musicalzodiacpiano.dagger.scope.FragmentScope
import com.keltapps.musicalzodiacpiano.menu.presentation.view.MenuFragment
import com.keltapps.musicalzodiacpiano.menu.presentation.viewmodel.*
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