package com.keltapps.makrokosmos.makrokosmos.song.data.resourceprovider.util.robot

import com.keltapps.makrokosmos.audio.client.domain.entity.PlayingState
import com.keltapps.makrokosmos.main.view.MainActivity
import junit.framework.Assert.assertFalse

class MainRobot(private val activity: MainActivity) {

    fun checkMusicIsPlaying() {
        assert(activity.audioRepository.getCurrentPlayingState() is PlayingState.Playing)
    }

    fun checkMusicIsNotPlaying() {
        assertFalse(activity.audioRepository.getCurrentPlayingState() is PlayingState.Playing)
    }
}
/*

fun mainRobot(func: MainRobot.() -> Unit) = MainRobot()
        .apply { func() }*/
