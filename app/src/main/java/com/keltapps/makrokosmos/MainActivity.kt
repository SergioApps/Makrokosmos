package com.keltapps.makrokosmos

import android.content.Context
import android.os.Bundle
import android.support.v4.media.*
import android.support.v4.media.session.*
import com.keltapps.makrokosmos.audio.client.MediaBrowserHelper
import com.keltapps.makrokosmos.audio.service.MusicService
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var mediaBrowserHelper: MediaBrowserHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaBrowserHelper = MediaBrowserConnection(this)
        mediaBrowserHelper.registerCallback(MediaBrowserListener())
    }
    //    window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

    override fun onStart() {
        super.onStart()
        mediaBrowserHelper.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
        mediaBrowserHelper.onStop()
    }
}

private class MediaBrowserConnection constructor(context: Context) : MediaBrowserHelper(context, MusicService::class.java) {

    override fun onConnected(mediaController: MediaControllerCompat) {
        //mSeekBarAudio.setMediaController(mediaController)
    }

    override fun onChildrenLoaded(
            parentId: String,
            children: List<MediaBrowserCompat.MediaItem>
    ) {
        super.onChildrenLoaded(parentId, children)

        val mediaController = mMediaController

        // Queue up all media items for this simple sample.
        for (mediaItem in children) {
            mediaController?.addQueueItem(mediaItem.description)
        }

        // Call prepare now so pressing play just works.
        mediaController?.transportControls?.prepare()
        mediaController?.transportControls?.play()
    }
}

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
        /*  mTitleTextView.setText(
                  mediaMetadata.getString(MediaMetadataCompat.METADATA_KEY_TITLE))
          mArtistTextView.setText(
                  mediaMetadata.getString(MediaMetadataCompat.METADATA_KEY_ARTIST))
          mAlbumArt.setImageBitmap(MusicLibrary.getAlbumBitmap(
                  this@MainActivity,
                  mediaMetadata.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID)))*/
    }

    override fun onSessionDestroyed() {
        super.onSessionDestroyed()
    }

    override fun onQueueChanged(queue: List<MediaSessionCompat.QueueItem>?) {
        super.onQueueChanged(queue)
    }
}

