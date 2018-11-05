package com.keltapps.makrokosmos.makrokosmos.song.data.resourceprovider.base

import android.graphics.*
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import org.hamcrest.*

class DrawableMatcher(private val expectedId: Int) : TypeSafeMatcher<View>() {

    override fun describeTo(description: Description?) {}

    override fun matchesSafely(item: View?): Boolean {
        val resources = item?.context?.resources
        val expectedDrawable = resources?.getDrawable(expectedId)
        return (item as? ImageView)?.drawable?.bitmap()?.sameAs(expectedDrawable?.bitmap()) ?: false
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