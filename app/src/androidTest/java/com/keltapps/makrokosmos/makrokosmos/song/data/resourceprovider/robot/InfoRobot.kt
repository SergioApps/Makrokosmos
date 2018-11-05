package com.keltapps.makrokosmos.makrokosmos.song.data.resourceprovider.robot

import androidx.test.espresso.ViewInteraction
import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.makrokosmos.song.data.resourceprovider.base.BaseTestRobot

class InfoRobot : BaseTestRobot() {

    fun matchAbout(): ViewInteraction {
        return   matchText(R.id.textView, R.string.infoAbout_body)
     //    matchImageView(R.id.imageViewInfo, R.drawable.about)
    }
     fun matchAuthor(): ViewInteraction {
         return    matchText(R.id.textView, R.string.infoAuthor_body)
       // return matchImageView(R.id.imageViewInfo, R.drawable.author)
    }
    fun matchInterpreter(): ViewInteraction {
        return   matchText(R.id.textView, R.string.infoInterpreter_body)
    //    return matchImageView(R.id.imageViewInfo, R.drawable.interpreter)
    }
}

fun info(func: InfoRobot.() -> Unit) = InfoRobot()
        .apply { func() }