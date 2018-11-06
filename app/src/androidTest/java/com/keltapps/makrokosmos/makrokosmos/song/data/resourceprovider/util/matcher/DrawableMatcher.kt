package com.keltapps.makrokosmos.makrokosmos.song.data.resourceprovider.util.matcher

import android.graphics.*
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description

class DrawableMatcher(private val expectedId: Int) : BoundedMatcher<View, ImageView>(ImageView::class.java) {

    override fun describeTo(description: Description?) {}

    override fun matchesSafely(item: ImageView?): Boolean {
        val resources = item?.context?.resources
        val expectedDrawable = resources?.getDrawable(expectedId)
        return item?.drawable?.bitmap()?.sameAs(expectedDrawable?.bitmap()) ?: false
    }

    private fun Drawable.bitmap(): Bitmap {
        val bitmap = Bitmap.createBitmap(
                intrinsicWidth,
                intrinsicHeight,
                Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        setBounds(0, 0, canvas.width, canvas.height)
        draw(canvas)
        return bitmap
    }
}