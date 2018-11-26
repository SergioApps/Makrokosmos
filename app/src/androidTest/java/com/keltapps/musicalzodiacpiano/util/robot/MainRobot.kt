package com.keltapps.musicalzodiacpiano.util.robot

import com.keltapps.musicalzodiacpiano.MakrokosmosApplication
import com.keltapps.musicalzodiacpiano.audio.client.domain.entity.PlayingState
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
