package com.keltapps.makrokosmos.makrokosmos.song.data.resourceprovider.util.robot

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.keltapps.makrokosmos.makrokosmos.song.data.resourceprovider.util.matcher.*
import com.schibsted.spain.barista.interaction.BaristaListInteractions.scrollListToPosition
import org.hamcrest.Matchers.allOf
import java.lang.Exception


open class BaseTestRobot {

    fun clickButton(resId: Int): ViewInteraction = onView((withId(resId))).perform(ViewActions.click())

    fun clickButtonWithText(text: Int): ViewInteraction = onView((withText(text))).perform(ViewActions.click())

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

    fun scrollToPositionRecyclerView(resId: Int, position: Int) {
        //return onView(allOf(withId(resId), isDisplayed())).perform(scrollToPosition<RecyclerView.ViewHolder>(position))
        scrollListToPosition(resId, position)
    }

    fun scrollToPositionRecyclerView2(resId: Int, text: Int) {
        /* return onView(allOf(withId(resId), isDisplayed()))
                 .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(position, NestedScrollToAction.scrollTo()))*/
        //return onView(withText(text)).perform(NestedScrollToAction.scrollTo())
        //scrollLToView(text)
        var result = true
        while (result) {
            result = try {
                onView(withText(text)).check(matches(isDisplayed()))
                true
            } catch (e: Exception) {
                onView(allOf(withId(resId), isDisplayed())).perform(ViewActions.swipeUp())
                false
            }
        }


    }

    fun clickListItem(listRes: Int, position: Int) {
        onView(allOf(withId(listRes), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position, click()))
    }

    fun matchCollapsingToolbarTitle(resId: Int, text: Int): ViewInteraction {
        return view(resId).check(matches(CollapsingToolbarTitleMatcher(text)))
    }

    fun matchToolbarTitle(resId: Int, text: Int): ViewInteraction {
        return view(resId).check(matches(ToolbarTitleMatcher(text)))
    }

    fun matchTabLayout(resId: Int, textIdList: List<Int>): ViewInteraction {
        return view(resId).check(matches(TabLayoutMatcher(textIdList)))
    }

    fun pressUp(): ViewInteraction {
        return onView(withContentDescription("Navigate up")).perform(click())
    }
}