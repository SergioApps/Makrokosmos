package com.keltapps.makrokosmos.makrokosmos.song.data.resourceprovider.util.matcher

import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description

class ToolbarTitleMatcher(
        private val textId: Int
) : BoundedMatcher<View, Toolbar>(Toolbar::class.java) {

    override fun describeTo(description: Description?) {}

    override fun matchesSafely(item: Toolbar?): Boolean {
        return item?.let { it.context.resources.getString(textId) == it.title } ?: false
    }
}