package com.keltapps.makrokosmos.makrokosmos.song.data.resourceprovider.util.matcher

import android.graphics.Rect
import android.view.View
import android.widget.*
import androidx.core.widget.NestedScrollView
import androidx.test.espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.util.HumanReadables
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Matcher

class NestedScrollToAction : ViewAction {

    override fun getDescription(): String = ""

    override fun getConstraints(): Matcher<View> {
        return allOf(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE), isDescendantOfA(anyOf(
                isAssignableFrom(ScrollView::class.java),
                isAssignableFrom(HorizontalScrollView::class.java),
                isAssignableFrom(NestedScrollView::class.java))))
    }

    override fun perform(uiController: UiController?, view: View?) {
        if (isDisplayingAtLeast(90).matches(view)) {
            return
        }
        val rect = Rect()
        view?.getDrawingRect(rect)
        uiController?.loopMainThreadUntilIdle()
        if (!isDisplayingAtLeast(90).matches(view)) {
            throw PerformException.Builder()
                    .withActionDescription(this.description)
                    .withViewDescription(HumanReadables.describe(view))
                    .withCause(RuntimeException(
                            "Scrolling to view was attempted, but the view is not displayed"))
                    .build()
        }
    }
    companion object {
        fun scrollTo(): ViewAction {
            return ViewActions.actionWithAssertions(NestedScrollToAction())
        }
    }
}