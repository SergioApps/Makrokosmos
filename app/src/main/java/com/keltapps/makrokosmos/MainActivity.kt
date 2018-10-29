package com.keltapps.makrokosmos

import android.os.Bundle
import com.keltapps.makrokosmos.audio.client.domain.repository.AudioRepository
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    internal lateinit var audioRepository: AudioRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
