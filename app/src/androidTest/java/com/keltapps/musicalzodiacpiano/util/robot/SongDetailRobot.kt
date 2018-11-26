package com.keltapps.musicalzodiacpiano.util.robot

import com.keltapps.musicalzodiacpiano.R

class SongDetailRobot : BaseTestRobot() {
    private fun matchSongTitle(textId: Int) {
        matchText(R.id.textViewTitle, textId)
    }

    private fun matchSongSubTitle(textId: Int) {
        matchText(R.id.textView4, textId)
    }

    private fun matchZodiacName(textId: Int) {
        matchText(R.id.zodiacSignName, textId)
    }

    private fun matchDuration(text: String) {
        matchText(R.id.songDetailDuration, text)
    }

    fun matchVolume1Song1() {
        matchSongTitle(R.string.volumeIFirstSongTitle)
        matchSongSubTitle(R.string.volumeIFirstSongSubTitle)
        matchZodiacName(R.string.zodiacSign_cancer)
        matchDuration("4:15")
    }

    fun matchVolume1Song3() {
        matchSongTitle(R.string.volumeIThirdSongTitle)
        matchSongSubTitle(R.string.volumeIThirdSongSubTitle)
        matchZodiacName(R.string.zodiacSign_taurus)
        matchDuration("1:55")
    }

    fun matchVolume1Song4() {
        matchSongTitle(R.string.volumeIFourthSongTitle)
        matchSongSubTitle(R.string.volumeIFourthSongSubTitle)
        matchZodiacName(R.string.zodiacSign_capricorn)
        matchDuration("2:22")
    }

    fun matchVolume1Song12() {
        matchSongTitle(R.string.volumeITwelfthSongTitle)
        matchSongSubTitle(R.string.volumeITwelfthSongSubTitle)
        matchZodiacName(R.string.zodiacSign_aquarius)
        matchDuration("2:40")
    }

    fun matchVolume2Song1() {
        matchSongTitle(R.string.volumeIIFirstSongTitle)
        matchSongSubTitle(R.string.volumeIIFirstSongSubTitle)
        matchZodiacName(R.string.zodiacSign_cancer)
        matchDuration("2:37")
    }

    fun matchVolume2Song12() {
        matchSongTitle(R.string.volumeIITwelfthSongTitle)
        matchSongSubTitle(R.string.volumeIITwelfthSongSubTitle)
        matchZodiacName(R.string.zodiacSign_capricorn)
        matchDuration("3:29")
    }


    fun clickPlayOrPause() {
        clickButton(R.id.playOrPauseSongDetail)
    }

    fun clickSkipToNext() {
        clickButton(R.id.skipNext)
    }

    fun clickSkipToPrevious() {
        clickButton(R.id.skipPrevious)
    }
}

fun songDetail(func: SongDetailRobot.() -> Unit) = SongDetailRobot()
        .apply { func() }