package com.keltapps.makrokosmos.makrokosmos.song.data.resourceprovider.base

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.*

open class BaseTestRobot {

    fun clickButton(resId: Int): ViewInteraction = onView((withId(resId))).perform(ViewActions.click())

    fun matchText(resId: Int, text: String): ViewInteraction = matchText(view(resId), text)

    fun matchText(resId: Int, text: Int): ViewInteraction = matchText(view(resId), text)

    private fun matchText(viewInteraction: ViewInteraction, text: String): ViewInteraction {
        return viewInteraction.check(ViewAssertions.matches(ViewMatchers.withText(text)))
    }

    private fun matchText(viewInteraction: ViewInteraction, text: Int): ViewInteraction {
        return viewInteraction.check(ViewAssertions.matches(ViewMatchers.withText(text)))
    }

    private fun view(resId: Int): ViewInteraction = onView(withId(resId))

    fun matchImageView(resId: Int, drawable: Int): ViewInteraction = matchImage(view(resId), drawable)

    private fun matchImage(viewInteraction: ViewInteraction, drawable: Int): ViewInteraction {
        return viewInteraction.check(matches(DrawableMatcher(drawable)))
    }

    fun scrollToView(resId: Int) = scrollToView(view(resId))

    private fun scrollToView(viewInteraction: ViewInteraction): ViewInteraction {
        return viewInteraction.perform(scrollTo())
    }

    fun clickListItem(listRes: Int, position: Int) {
        onData(anything())
                .inAdapterView(allOf(withId(listRes)))
                .atPosition(position).perform(ViewActions.click())
    }

    fun pressUp(): ViewInteraction {
        return onView(withContentDescription("Navigate up")).perform(click())
    }
}