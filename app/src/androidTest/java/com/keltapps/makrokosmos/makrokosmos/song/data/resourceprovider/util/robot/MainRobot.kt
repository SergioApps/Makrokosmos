package com.keltapps.makrokosmos.makrokosmos.song.data.resourceprovider.util.robot

import com.keltapps.makrokosmos.MakrokosmosApplication
import com.keltapps.makrokosmos.audio.client.domain.entity.PlayingState
import com.keltapps.makrokosmos.main.view.MainActivity
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
