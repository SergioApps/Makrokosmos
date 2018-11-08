package com.keltapps.makrokosmos.makrokosmos.song.data.resourceprovider.util.robot

import com.keltapps.makrokosmos.R

class PlayBarRobot : BaseTestRobot() {

    fun checkPlayBarIsDisplayed() {
        viewIsDisplayed(R.id.playBarToolbar)
    }

    fun clickPlayOrPause() {
        clickButton(R.id.playBarPlayPauseContainer)
    }

    fun clickBar() {
        clickButton(R.id.playBarToolbar)
    }
}

fun playBar(func: PlayBarRobot.() -> Unit) = PlayBarRobot()
        .apply { func() }