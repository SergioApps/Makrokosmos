package com.keltapps.makrokosmos.dagger.module.main.presentation

import android.arch.lifecycle.ViewModelProviders
import com.keltapps.makrokosmos.base.view.createFactory
import com.keltapps.makrokosmos.dagger.scope.ActivityScope
import com.keltapps.makrokosmos.main.presentation.view.MainActivity
import com.keltapps.makrokosmos.main.presentation.viewmodel.MainViewModel
import com.keltapps.makrokosmos.main.presentation.viewmodel.MakrokosmosMainViewModel
import com.keltapps.makrokosmos.navigation.MakrokosmosNavigator
import com.keltapps.makrokosmos.navigation.Navigator
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    @ActivityScope
    fun provideMainViewModel(
            activity: MainActivity,
            navigator: Navigator
    ): MainViewModel {
        val viewModel = MakrokosmosMainViewModel(navigator)
        val viewModelFactory = viewModel.createFactory()
        return ViewModelProviders.of(activity, viewModelFactory).get(viewModel.javaClass)
    }

    @Provides
    @ActivityScope
    fun provideNavigator(activity: MainActivity): Navigator {
        return MakrokosmosNavigator(activity)
    }
}