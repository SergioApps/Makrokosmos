package com.keltapps.musicalzodiacpiano.dagger.module.info.presentation

import androidx.lifecycle.ViewModelProviders
import com.keltapps.musicalzodiacpiano.base.presentation.view.createFactory
import com.keltapps.musicalzodiacpiano.dagger.scope.FragmentScope
import com.keltapps.musicalzodiacpiano.info.presentation.model.InfoScreen
import com.keltapps.musicalzodiacpiano.info.presentation.view.InfoFragment
import com.keltapps.musicalzodiacpiano.info.presentation.annotation.ProvideInfoScreen
import com.keltapps.musicalzodiacpiano.info.presentation.viewmodel.InfoViewModel
import com.keltapps.musicalzodiacpiano.info.presentation.viewmodel.MakrokosmosInfoViewModel
import dagger.Module
import dagger.Provides

@Module
class InfoModule {

    @Provides
    @FragmentScope
    fun provideInfoViewModel(
            fragment: InfoFragment,
            viewModel: MakrokosmosInfoViewModel
    ): InfoViewModel {
        val viewModelFactory = viewModel.createFactory()
        return ViewModelProviders.of(fragment, viewModelFactory).get(viewModel.javaClass)
    }

    @Provides
    @ProvideInfoScreen
    fun provideInfoScreen(fragment: InfoFragment): InfoScreen = fragment.getInfoScreen()
}
