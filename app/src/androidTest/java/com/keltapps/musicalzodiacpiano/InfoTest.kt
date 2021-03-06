package com.keltapps.musicalzodiacpiano

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.keltapps.musicalzodiacpiano.main.view.MainActivity
import com.keltapps.musicalzodiacpiano.util.robot.info
import com.keltapps.musicalzodiacpiano.util.robot.menu
import com.keltapps.musicalzodiacpiano.util.setLandscape
import com.keltapps.musicalzodiacpiano.util.setPortrait
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InfoTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAboutInfoPortrait() {
        activityRule.setPortrait()
        menu {
            matchMenu()
            clickAbout()
        }
        info {
            matchAboutPortrait()
            pressUp()
        }
        menu {
            matchMenu()
        }
    }

    @Test
    fun testAboutInfoLandscape() {
        activityRule.setLandscape()
        menu {
            matchMenu()
            clickAbout()
        }
        info {
            matchAboutLandscape()
            pressUp()
        }
        menu {
            matchMenu()
        }
    }

    @Test
    fun testAuthorInfoPortrait() {
        activityRule.setPortrait()
        menu {
            matchMenu()
            clickAuthor()
        }
        info {
            matchAuthorPortrait()
            pressUp()
        }
        menu {
            matchMenu()
        }
    }

    @Test
    fun testAuthorInfoLandscape() {
        activityRule.setLandscape()
        menu {
            matchMenu()
            clickAuthor()
        }
        info {
            matchAuthorLandscape()
            pressUp()
        }
        menu {
            matchMenu()
        }
    }

    @Test
    fun testInterpreterInfoPortrait() {
        activityRule.setPortrait()
        menu {
            matchMenu()
            clickInterpreter()
        }
        info {
            matchInterpreterPortrait()
            pressUp()
        }
        menu {
            matchMenu()
        }
    }

    @Test
    fun testInterpreterInfoLandscape() {
        activityRule.setLandscape()
        menu {
            matchMenu()
            clickInterpreter()
        }
        info {
            matchInterpreterLandscape()
            pressUp()
        }
        menu {
            matchMenu()
        }
    }
}