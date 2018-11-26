package com.keltapps.musicalzodiacpiano

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.keltapps.musicalzodiacpiano.main.view.MainActivity
import com.keltapps.musicalzodiacpiano.util.robot.*
import com.keltapps.musicalzodiacpiano.util.setLandscape
import com.keltapps.musicalzodiacpiano.util.setPortrait
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SongTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testSongListDisplayBothVolumesPortrait() {
        activityRule.setPortrait()
        testSongListDisplayBothVolumes()
    }

    @Test
    fun testSongListDisplayBothVolumesLandscape() {
        activityRule.setLandscape()
        testSongListDisplayBothVolumes()
    }

    private fun testSongListDisplayBothVolumes() {
        menu {
            matchMenu()
            clickMakrokosmos()
        }
        songList {
            matchHeader()
            matchVolume1()
            clickVolume2()
            matchVolume2()
            clickVolume1()
            matchVolume1()
            pressUp()
        }
        menu {
            matchMenu()
        }
    }

    @Test
    fun testSongListOpenSongDetailPortrait() {
        activityRule.setPortrait()
        testSongListOpenSongDetail()
    }

    @Test
    fun testSongListOpenSongDetailLandscape() {
        activityRule.setLandscape()
        testSongListOpenSongDetail()
    }

    private fun testSongListOpenSongDetail() {
        menu {
            matchMenu()
            clickMakrokosmos()
        }
        songList {
            matchHeader()
            matchVolume1()
            clickSong(3)
        }
        songDetail {
            matchVolume1Song3()
            mainRobot { checkMusicIsPlaying() }
            clickPlayOrPause()
            mainRobot { checkMusicIsNotPlaying() }
            clickSkipToNext()
            matchVolume1Song4()
            pressUp()
            mainRobot { checkMusicIsPlaying() }
        }
        songList {
            matchHeader()
            matchVolume1()
            pressUp()
        }
        menu { matchMenu() }
        playBar { clickBar() }
        songDetail {
            mainRobot { checkMusicIsPlaying() }
            clickPlayOrPause()
            mainRobot { checkMusicIsNotPlaying() }
        }
    }

    @Test
    fun testSongListOpenSongDetailMoveBetweenVolumesPortrait() {
        activityRule.setPortrait()
        testSongListOpenSongDetailMoveBetweenVolume()
    }

    @Test
    fun testSongListOpenSongDetailMoveBetweenVolumeLandscape() {
        activityRule.setLandscape()
        testSongListOpenSongDetailMoveBetweenVolume()
    }

    private fun testSongListOpenSongDetailMoveBetweenVolume() {
        menu {
            matchMenu()
            clickMakrokosmos()
        }
        songList {
            matchHeader()
            matchVolume1()
            clickSong(12)
        }
        songDetail {
            matchVolume1Song12()
            mainRobot { checkMusicIsPlaying() }
            clickSkipToNext()
            matchVolume2Song1()
            clickSkipToPrevious()
            matchVolume1Song12()
            pressUp()
            mainRobot { checkMusicIsPlaying() }
        }
        songList {
            matchHeader()
            matchVolume1()
        }
        playBar {
            checkPlayBarIsDisplayed()
            clickPlayOrPause()
            mainRobot { checkMusicIsNotPlaying() }
        }
    }

    @Test
    fun testSongListOpenSongDetailMoveFinalToBeginningPortrait() {
        activityRule.setPortrait()
        testSongListOpenSongDetailMoveFinalToBeginning()
    }

    @Test
    fun testSongListOpenSongDetailMoveFinalToBeginningLandscape() {
        activityRule.setLandscape()
        testSongListOpenSongDetailMoveFinalToBeginning()
    }

    private fun testSongListOpenSongDetailMoveFinalToBeginning() {
        menu {
            matchMenu()
            clickMakrokosmos()
        }
        songList {
            matchHeader()
            clickVolume2()
            matchVolume2()
            clickSong(12)
        }
        songDetail {
            matchVolume2Song12()
            mainRobot { checkMusicIsPlaying() }
            clickSkipToNext()
            matchVolume1Song1()
            clickSkipToPrevious()
            matchVolume2Song12()
            pressUp()
        }
        mainRobot { checkMusicIsPlaying() }
        songList {
            matchHeader()
            matchVolume2()
        }
        playBar {
            checkPlayBarIsDisplayed()
            clickPlayOrPause()
            mainRobot { checkMusicIsNotPlaying() }
        }
    }

    @Test
    fun testShowPlayBarPortrait() {
        activityRule.setPortrait()
        testShowPlayBar()
    }

    @Test
    fun testShowPlayBarLandscape() {
        activityRule.setLandscape()
        testShowPlayBar()
    }

    private fun testShowPlayBar() {
        menu {
            matchMenu()
            clickMakrokosmos()
        }
        songList {
            matchHeader()
            clickSong(11)
        }
        mainRobot { checkMusicIsPlaying() }
        songDetail {
            pressUp()
        }
        playBar {
            checkPlayBarIsDisplayed()
        }
        songList {
            mainRobot { checkMusicIsPlaying() }
            matchHeader()
            pressUp()
        }
        playBar {
            checkPlayBarIsDisplayed()
        }
        menu {
            matchMenu()
            clickAbout()
        }
        playBar {
            checkPlayBarIsDisplayed()
            pressUp()
        }
        menu {
            matchMenu()
            clickAuthor()
        }
        playBar {
            checkPlayBarIsDisplayed()
            pressUp()
        }
        menu {
            matchMenu()
            clickInterpreter()
        }
        playBar {
            checkPlayBarIsDisplayed()
            clickPlayOrPause()
            mainRobot { checkMusicIsNotPlaying() }
        }
    }
}