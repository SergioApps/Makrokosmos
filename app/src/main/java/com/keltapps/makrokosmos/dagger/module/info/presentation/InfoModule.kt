package com.keltapps.makrokosmos.dagger.module.info.presentation

import android.arch.lifecycle.ViewModelProviders
import com.keltapps.makrokosmos.base.presentation.view.createFactory
import com.keltapps.makrokosmos.dagger.scope.FragmentScope
import com.keltapps.makrokosmos.info.presentation.view.InfoFragment
import com.keltapps.makrokosmos.info.presentation.viewmodel.InfoViewModel
import com.keltapps.makrokosmos.info.presentation.viewmodel.MakrokosmosInfoViewModel
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
}