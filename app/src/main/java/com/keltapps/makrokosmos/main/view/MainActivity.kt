package com.keltapps.makrokosmos.main.view

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.audio.client.domain.repository.AudioRepository
import com.keltapps.makrokosmos.databinding.ActivityMainBinding
import com.keltapps.makrokosmos.main.viewmodel.MainViewModel
import com.keltapps.makrokosmos.navigation.Navigator
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.toolbar_play.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    internal lateinit var audioRepository: AudioRepository

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
        viewModel.initialize()
        handleOpenSongDetails()
        handleToolbarPlayVisibility()
    }

    private fun handleOpenSongDetails() {
        viewModel.openSongDetail.observe(this, Observer {
            navigator.openSongDetail(
                    findNavController(R.id.my_nav_host_fragment),
                    it
            )
        })
    }

    private fun handleToolbarPlayVisibility() {
        viewModel.isPlaying.observe(this, Observer {
            findNavController(R.id.my_nav_host_fragment).addOnNavigatedListener { _, destination ->
                val isSongDetailFragment = destination.id == R.id.songDetailFragment
                toolbar2?.visibility = if (it && !isSongDetailFragment) View.VISIBLE else View.GONE
            }
        })
    }
    //    window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

    override fun onStart() {
        super.onStart()
        audioRepository.start()
    }

    override fun onStop() {
        super.onStop()
        audioRepository.stop()
    }
}
