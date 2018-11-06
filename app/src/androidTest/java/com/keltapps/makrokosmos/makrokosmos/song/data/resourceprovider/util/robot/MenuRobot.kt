package com.keltapps.makrokosmos.makrokosmos.song.data.resourceprovider.util.robot

import androidx.test.espresso.ViewInteraction
import com.keltapps.makrokosmos.R

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
        matchText(R.id.textViewMakrokosmos, R.string.main_makrokosmos)
        return matchImageView(R.id.imageViewMakrokosmos, R.drawable.cd_cover)
    }

    private fun matchAbout(): ViewInteraction {
        scrollToView(R.id.aboutContainer)
        matchText(R.id.textViewAbout, R.string.main_about)
        return matchImageView(R.id.imageViewAbout, R.drawable.about)
    }

    private fun matchAuthor(): ViewInteraction {
        scrollToView(R.id.authorContainer)
        matchText(R.id.textViewAuthor, R.string.main_author)
        return matchImageView(R.id.imageViewAuthor, R.drawable.author)
    }

    private fun matchInterpreter(): ViewInteraction {
        scrollToView(R.id.interpreterContainer)
        matchText(R.id.textViewInterpreter, R.string.main_interpreter)
        return matchImageView(R.id.imageViewInterpreter, R.drawable.interpreter)
    }
}

fun menu(func: MenuRobot.() -> Unit) = MenuRobot()
        .apply { func() }