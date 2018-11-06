package com.keltapps.makrokosmos.makrokosmos.song.data.resourceprovider.util

import android.app.Activity
import androidx.test.rule.ActivityTestRule

fun <T : Activity> ActivityTestRule<T>.setPortrait() {
    this.activity.requestedOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
}
fun <T : Activity> ActivityTestRule<T>.setLandscape() {
    this.activity.requestedOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
}