package com.keltapps.makrokosmos.makrokosmos.song.data.resourceprovider

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.keltapps.makrokosmos.main.view.MainActivity
import com.keltapps.makrokosmos.makrokosmos.song.data.resourceprovider.util.*
import com.keltapps.makrokosmos.makrokosmos.song.data.resourceprovider.util.robot.*
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
            checkMusicIsPlaying()
            clickPlayOrPause()
            checkMusicIsNotPlaying()
            clickSkipToNext()
            matchVolume1Song4()
            pressUp()
            checkMusicIsPlaying()
        }
        songList {
            matchHeader()
            matchVolume1()
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
        testSongListOpenSongDetail()
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
            checkMusicIsPlaying()
            clickSkipToNext()
            matchVolume2Song1()
            clickSkipToPrevious()
            matchVolume1Song12()
            pressUp()
            checkMusicIsPlaying()
        }
        songList {
            matchHeader()
            matchVolume1()
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
            checkMusicIsPlaying()
            clickSkipToNext()
            matchVolume1Song1()
            clickSkipToPrevious()
            matchVolume2Song12()
            pressUp()
            checkMusicIsPlaying()
        }
        songList {
            matchHeader()
            matchVolume2()
        }
    }
}