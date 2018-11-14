package com.keltapps.makrokosmos.util.robot

import com.keltapps.makrokosmos.MakrokosmosApplication
import com.keltapps.makrokosmos.audio.client.domain.entity.PlayingState
import junit.framework.Assert.assertFalse

class MainRobot {

    fun checkMusicIsPlaying() {
        assert(
                MakrokosmosApplication.appComponent.provideAudioRepository().getCurrentPlayingState() is PlayingState.Playing
        )
    }

    fun checkMusicIsNotPlaying() {
        assertFalse(
                MakrokosmosApplication.appComponent.provideAudioRepository().getCurrentPlayingState() is PlayingState.Playing
        )
    }
}

fun mainRobot(func: MainRobot.() -> Unit) = MainRobot()
        .apply { func() }
