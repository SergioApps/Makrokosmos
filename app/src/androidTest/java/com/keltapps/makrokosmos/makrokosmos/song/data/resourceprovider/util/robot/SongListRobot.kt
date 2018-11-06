package com.keltapps.makrokosmos.makrokosmos.song.data.resourceprovider.util.robot

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.makrokosmos.song.data.resourceprovider.util.matcher.AtRecyclerViewPosition
import org.hamcrest.Matchers.allOf

class SongListRobot : BaseTestRobot() {

    fun matchHeader(): ViewInteraction {
        matchTabLayout(R.id.tabLayout, listOf(R.string.tab_name_volume_1, R.string.tab_name_volume_2))
        return matchToolbarTitle(R.id.toolbar, R.string.title_songListActivity)
    }

    private fun matchRecyclerViewItem(
            position: Int,
            titleId: Int,
            signName: Int
    ): ViewInteraction {
        return onView(allOf(withId(R.id.recyclerView), isDisplayed()))
                .check(matches(allOf(
                        AtRecyclerViewPosition(position, hasDescendant(withText(titleId))),
                        AtRecyclerViewPosition(position, hasDescendant(withText(signName)))
                )))
    }

    fun matchVolume1() {
        matchVolume1Song1()
        matchVolume1Song2()
        matchVolume1Song3()
        matchVolume1Song4()
        matchVolume1Song5()
        matchVolume1Song6()
        matchVolume1Song7()
        matchVolume1Song8()
        matchVolume1Song9()
        matchVolume1Song10()
        matchVolume1Song11()
        matchVolume1Song12()
    }

    private fun matchVolume1Song1() {
        scrollToPositionRecyclerView(R.id.recyclerView, 0)
        matchRecyclerViewItem(
                0,
                R.string.volumeIFirstSongTitle,
                R.string.zodiacSign_cancer
        )
    }

    private fun matchVolume1Song2() {
        scrollToPositionRecyclerView(R.id.recyclerView, 1)
        matchRecyclerViewItem(
                1,
                R.string.volumeISecondSongTitle,
                R.string.zodiacSign_pisces
        )
    }

    private fun matchVolume1Song3() {
        scrollToPositionRecyclerView(R.id.recyclerView, 2)
        matchRecyclerViewItem(
                2,
                R.string.volumeIThirdSongTitle,
                R.string.zodiacSign_taurus
        )
    }

    private fun matchVolume1Song4() {
        scrollToPositionRecyclerView(R.id.recyclerView, 3)
        matchRecyclerViewItem(
                3,
                R.string.volumeIFourthSongTitle,
                R.string.zodiacSign_capricorn
        )
    }

    private fun matchVolume1Song5() {
        scrollToPositionRecyclerView(R.id.recyclerView, 4)
        matchRecyclerViewItem(
                4,
                R.string.volumeIFifthSongTitle,
                R.string.zodiacSign_scorpio
        )
    }

    private fun matchVolume1Song6() {
        scrollToPositionRecyclerView(R.id.recyclerView, 5)
        matchRecyclerViewItem(
                5,
                R.string.volumeISixthSongTitle,
                R.string.zodiacSign_sagittarius
        )
    }

    private fun matchVolume1Song7() {
        scrollToPositionRecyclerView(R.id.recyclerView, 6)
        matchRecyclerViewItem(
                6,
                R.string.volumeISeventhSongTitle,
                R.string.zodiacSign_libra
        )
    }

    private fun matchVolume1Song8() {
        scrollToPositionRecyclerView(R.id.recyclerView, 7)
        matchRecyclerViewItem(
                7,
                R.string.volumeIEighthSongTitle,
                R.string.zodiacSign_leo
        )
    }

    private fun matchVolume1Song9() {
        scrollToPositionRecyclerView(R.id.recyclerView, 8)
        matchRecyclerViewItem(
                8,
                R.string.volumeINinthSongTitle,
                R.string.zodiacSign_virgo
        )
    }

    private fun matchVolume1Song10() {
        scrollToPositionRecyclerView(R.id.recyclerView, 9)
        matchRecyclerViewItem(
                9,
                R.string.volumeITenthSongTitle,
                R.string.zodiacSign_aries
        )
    }

    private fun matchVolume1Song11() {
        scrollToPositionRecyclerView(R.id.recyclerView, 10)
        matchRecyclerViewItem(
                10,
                R.string.volumeIEleventhSongTitle,
                R.string.zodiacSign_gemini
        )
    }

    private fun matchVolume1Song12() {
        scrollToPositionRecyclerView(R.id.recyclerView, 11)
        matchRecyclerViewItem(
                11,
                R.string.volumeITwelfthSongTitle,
                R.string.zodiacSign_aquarius
        )
    }

    fun matchVolume2() {
        matchVolume2Song1()
        matchVolume2Song2()
        matchVolume2Song3()
        matchVolume2Song4()
        matchVolume2Song5()
        matchVolume2Song6()
        matchVolume2Song7()
        matchVolume2Song8()
        matchVolume2Song9()
        matchVolume2Song10()
        matchVolume2Song11()
        matchVolume2Song12()
    }

    private fun matchVolume2Song1() {
        scrollToPositionRecyclerView(R.id.recyclerView, 0)
        matchRecyclerViewItem(
                0,
                R.string.volumeIIFirstSongTitle,
                R.string.zodiacSign_cancer
        )
    }

    private fun matchVolume2Song2() {
        scrollToPositionRecyclerView(R.id.recyclerView, 1)
        matchRecyclerViewItem(
                1,
                R.string.volumeIISecondSongTitle,
                R.string.zodiacSign_sagittarius
        )
    }

    private fun matchVolume2Song3() {
        scrollToPositionRecyclerView(R.id.recyclerView, 2)
        matchRecyclerViewItem(
                2,
                R.string.volumeIIThirdSongTitle,
                R.string.zodiacSign_pisces
        )
    }

    private fun matchVolume2Song4() {
        scrollToPositionRecyclerView(R.id.recyclerView, 3)
        matchRecyclerViewItem(
                3,
                R.string.volumeIIFourthSongTitle,
                R.string.zodiacSign_gemini
        )
    }

    private fun matchVolume2Song5() {
        scrollToPositionRecyclerView(R.id.recyclerView, 4)
        matchRecyclerViewItem(
                4,
                R.string.volumeIIFifthSongTitle,
                R.string.zodiacSign_virgo
        )
    }

    private fun matchVolume2Song6() {
        scrollToPositionRecyclerView(R.id.recyclerView, 5)
        matchRecyclerViewItem(
                5,
                R.string.volumeIISixthSongTitle,
                R.string.zodiacSign_taurus
        )
    }

    private fun matchVolume2Song7() {
        scrollToPositionRecyclerView(R.id.recyclerView, 6)
        matchRecyclerViewItem(
                6,
                R.string.volumeIISeventhSongTitle,
                R.string.zodiacSign_scorpio
        )
    }

    private fun matchVolume2Song8() {
        scrollToPositionRecyclerView(R.id.recyclerView, 7)
        matchRecyclerViewItem(
                7,
                R.string.volumeIIEighthSongTitle,
                R.string.zodiacSign_aries
        )
    }

    private fun matchVolume2Song9() {
        scrollToPositionRecyclerView(R.id.recyclerView, 8)
        matchRecyclerViewItem(
                8,
                R.string.volumeIINinthSongTitle,
                R.string.zodiacSign_libra
        )
    }

    private fun matchVolume2Song10() {
        scrollToPositionRecyclerView(R.id.recyclerView, 9)
        matchRecyclerViewItem(
                9,
                R.string.volumeIITenthSongTitle,
                R.string.zodiacSign_aquarius
        )
    }

    private fun matchVolume2Song11() {
        scrollToPositionRecyclerView(R.id.recyclerView, 10)
        matchRecyclerViewItem(
                10,
                R.string.volumeIIEleventhSongTitle,
                R.string.zodiacSign_leo
        )
    }

    private fun matchVolume2Song12() {
        scrollToPositionRecyclerView(R.id.recyclerView, 11)
        matchRecyclerViewItem(
                11,
                R.string.volumeIITwelfthSongTitle,
                R.string.zodiacSign_capricorn
        )
    }

    fun clickVolume1() {
        clickButtonWithText(R.string.volumeITitle)
    }

    fun clickVolume2() {
        clickButtonWithText(R.string.volumeIITitle)
    }

    fun clickSong(songNumber: Int) {
        scrollToPositionRecyclerView(R.id.recyclerView, songNumber - 1)
        clickListItem(R.id.recyclerView, songNumber - 1)
    }
}

fun songList(func: SongListRobot.() -> Unit) = SongListRobot()
        .apply { func() }