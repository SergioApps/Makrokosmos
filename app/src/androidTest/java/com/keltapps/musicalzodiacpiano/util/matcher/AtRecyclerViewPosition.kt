package com.keltapps.musicalzodiacpiano.util.matcher

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.*

class AtRecyclerViewPosition(
        private val position: Int,
        private val itemMatcher: Matcher<View>
) : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
    override fun describeTo(description: Description?) {}

    override fun matchesSafely(item: RecyclerView?): Boolean {
        val viewHolder = item?.findViewHolderForAdapterPosition(position)
        return itemMatcher.matches(viewHolder?.itemView)
    }
}