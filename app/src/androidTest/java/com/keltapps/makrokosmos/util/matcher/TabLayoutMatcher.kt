package com.keltapps.makrokosmos.util.matcher

import android.view.View
import androidx.test.espresso.matcher.BoundedMatcher
import com.google.android.material.tabs.TabLayout
import org.hamcrest.Description

class TabLayoutMatcher(
        private val textIdList: List<Int>
) : BoundedMatcher<View, TabLayout>(TabLayout::class.java) {

    override fun describeTo(description: Description?) {}

    override fun matchesSafely(item: TabLayout?): Boolean {
        return item?.let {
            var result = it.tabCount == textIdList.size
            textIdList.forEachIndexed { index, id ->
                result = result && it.getTabAt(index)?.text?.equals(it.context.getString(id)) ?: false
            }
            result
        } ?: false
    }
}