package com.keltapps.makrokosmos

import android.os.Bundle
import com.keltapps.makrokosmos.audio.client.AudioRepository
import com.keltapps.makrokosmos.audio.client.MakrokosmosMediaBrowserHelper
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    internal lateinit var audioRepository: AudioRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //     makrokosmosMediaBrowserHelper = MakrokosmosMediaBrowserHelper(this)
        // makrokosmosMediaBrowserHelper.registerCallback(MediaBrowserListener())
    }
    //    window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

    override fun onStart() {
        super.onStart()
        audioRepository.start()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
        makrokosmosMediaBrowserHelper.onStop()
    }
}
/*
private class MediaBrowserConnection constructor(context: Context) : MakrokosmosMediaBrowserHelper(context, MusicService::class.java) {

    override fun onConnected(mediaController: MediaControllerCompat) {
        //mSeekBarAudio.setMediaController(mediaController)
    }

    override fun onChildrenLoaded(
            parentId: String,
            children: List<MediaBrowserCompat.MediaItem>
    ) {
        super.onChildrenLoaded(parentId, children)

        val mediaController = mediaController

        // Queue up all media items for this simple sample.
        for (mediaItem in children) {
            mediaController?.addQueueItem(mediaItem.description)
        }

        // Call prepare now so pressing start just works.
        mediaController?.transportControls?.prepare()
        mediaController?.transportControls?.start()
    }
}*/
/*
private class MediaBrowserListener : MediaControllerCompat.Callback() {
    private var mIsPlaying: Boolean = false
    override fun onPlaybackStateChanged(playbackState: PlaybackStateCompat?) {
        mIsPlaying = playbackState != null && playbackState.state == PlaybackStateCompat.STATE_PLAYING
        //  mMediaControlsImage.setPressed(mIsPlaying)
    }

    override fun onMetadataChanged(mediaMetadata: MediaMetadataCompat?) {
        if (mediaMetadata == null) {
            return
        }
    }
}*/

