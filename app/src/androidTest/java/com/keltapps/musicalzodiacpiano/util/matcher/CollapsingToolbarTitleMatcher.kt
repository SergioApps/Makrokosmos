package com.keltapps.musicalzodiacpiano.util.matcher

import android.view.View
import androidx.test.espresso.matcher.BoundedMatcher
import com.google.android.material.appbar.CollapsingToolbarLayout
import org.hamcrest.Description

class CollapsingToolbarTitleMatcher(
        private val textId: Int
) : BoundedMatcher<View, CollapsingToolbarLayout>(CollapsingToolbarLayout::class.java) {

    override fun describeTo(description: Description?) {}

    override fun matchesSafely(item: CollapsingToolbarLayout?): Boolean {
        return item?.let { it.context.resources.getString(textId) == it.title } ?: false
    }
}