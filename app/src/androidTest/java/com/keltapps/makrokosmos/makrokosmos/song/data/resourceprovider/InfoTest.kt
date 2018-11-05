package com.keltapps.makrokosmos.makrokosmos.song.data.resourceprovider

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.keltapps.makrokosmos.main.view.MainActivity
import com.keltapps.makrokosmos.makrokosmos.song.data.resourceprovider.robot.*
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InfoTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAboutInfo() {
        menu {
            matchMenu()
            clickAbout()
        }
        info {
            matchAbout()
            pressUp()
        }
        menu {
            matchMenu()
        }
    }

    @Test
    fun testAuthorInfo() {
        menu {
            matchMenu()
            clickAuthor()
        }
        info {
            matchAuthor()
            pressUp()
        }
        menu {
            matchMenu()
        }
    }

    @Test
    fun testInterpreterInfo() {
        menu {
            matchMenu()
            clickInterpreter()
        }
        info {
            matchInterpreter()
            pressUp()
        }
        menu {
            matchMenu()
        }
    }
}