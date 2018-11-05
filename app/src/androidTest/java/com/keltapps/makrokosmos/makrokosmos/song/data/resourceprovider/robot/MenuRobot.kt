package com.keltapps.makrokosmos.makrokosmos.song.data.resourceprovider.robot

import androidx.test.espresso.ViewInteraction
import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.makrokosmos.song.data.resourceprovider.base.BaseTestRobot

class MenuRobot : BaseTestRobot() {

    fun clickMakrokosmos(): ViewInteraction {
        scrollToView(R.id.makrokosmosContainer)
        return clickButton(R.id.makrokosmosContainer)
    }

    fun clickAbout(): ViewInteraction {
        scrollToView(R.id.aboutContainer)
        return clickButton(R.id.aboutContainer)
    }

    fun clickAuthor(): ViewInteraction {
        scrollToView(R.id.authorContainer)
        return clickButton(R.id.authorContainer)
    }

    fun clickInterpreter(): ViewInteraction {
        scrollToView(R.id.interpreterContainer)
        return clickButton(R.id.interpreterContainer)
    }

    fun matchMenu(): ViewInteraction {
        matchMakrokosmos()
        matchAbout()
        matchAuthor()
        return matchInterpreter()
    }

    private fun matchMakrokosmos(): ViewInteraction {
        scrollToView(R.id.makrokosmosContainer)
        return matchImageView(R.id.imageViewMakrokosmos, R.drawable.cd_cover)
    }

    private fun matchAbout(): ViewInteraction {
        scrollToView(R.id.aboutContainer)
        return matchImageView(R.id.imageViewAbout, R.drawable.about)
    }

    private fun matchAuthor(): ViewInteraction {
        scrollToView(R.id.authorContainer)
        return matchImageView(R.id.imageViewAuthor, R.drawable.author)
    }

    private fun matchInterpreter(): ViewInteraction {
        scrollToView(R.id.interpreterContainer)
        return matchImageView(R.id.imageViewInterpreter, R.drawable.interpreter)
    }
}

fun menu(func: MenuRobot.() -> Unit) = MenuRobot()
        .apply { func() }