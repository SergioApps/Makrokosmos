package com.keltapps.makrokosmos.main.view

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleRegistryOwner
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.audio.client.domain.repository.AudioRepository
import com.keltapps.makrokosmos.databinding.ActivityMainBinding
import com.keltapps.makrokosmos.main.viewmodel.MainViewModel
import com.keltapps.makrokosmos.navigation.Navigator
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    internal lateinit var viewModel: MainViewModel
    @Inject
    internal lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
                this,
                R.layout.activity_main
        )
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        lifecycle.addObserver(viewModel)
        binding.playBarTitle.isSelected = true
        handleOpenSongDetails()
        handleToolbarPlayVisibility()
    }

    private fun handleOpenSongDetails() {
        viewModel.openSongDetail.observe(this, Observer {
            navigator.openSongDetail(
                    navController(),
                    it
            )
        })
    }

    private fun handleToolbarPlayVisibility() {
        navController().addOnNavigatedListener { _, destination ->
            viewModel.destination.value = destination.id
        }
    }

    private fun navController() = findNavController(R.id.my_nav_host_fragment)
}
