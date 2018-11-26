package com.keltapps.musicalzodiacpiano.util.robot

import androidx.test.espresso.ViewInteraction
import com.keltapps.musicalzodiacpiano.R

class InfoRobot : BaseTestRobot() {

    fun matchAboutPortrait(): ViewInteraction {
        matchCollapsingToolbarTitle(R.id.collapsingToolbarLayout, R.string.infoAbout_title)
        return matchText(R.id.textView, R.string.infoAbout_body)
    }

    fun matchAuthorPortrait(): ViewInteraction {
        matchCollapsingToolbarTitle(R.id.collapsingToolbarLayout, R.string.infoAuthor_title)
        return matchText(R.id.textView, R.string.infoAuthor_body)
    }

    fun matchInterpreterPortrait(): ViewInteraction {
        matchCollapsingToolbarTitle(R.id.collapsingToolbarLayout, R.string.infoInterpreter_title)
        return matchText(R.id.textView, R.string.infoInterpreter_body)
    }

    fun matchAboutLandscape(): ViewInteraction {
        matchToolbarTitle(R.id.toolbar, R.string.infoAbout_title)
        return matchText(R.id.textView, R.string.infoAbout_body)
    }

    fun matchAuthorLandscape(): ViewInteraction {
        matchToolbarTitle(R.id.toolbar, R.string.infoAuthor_title)
        return matchText(R.id.textView, R.string.infoAuthor_body)
    }

    fun matchInterpreterLandscape(): ViewInteraction {
        matchToolbarTitle(R.id.toolbar, R.string.infoInterpreter_title)
        return matchText(R.id.textView, R.string.infoInterpreter_body)
    }
}

fun info(func: InfoRobot.() -> Unit) = InfoRobot()
        .apply { func() }