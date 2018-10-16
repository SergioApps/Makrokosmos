package com.keltapps.makrokosmos.song.data.repository

import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.song.data.resourceprovider.ResourceProvider
import com.keltapps.makrokosmos.song.domain.entity.*
import com.keltapps.makrokosmos.song.domain.repository.CDRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MakrokosmosCDRepository @Inject constructor(
        private val resourceProvider: ResourceProvider
) : CDRepository {

    override fun getCD(): Observable<CD> {
        return Observable.just(createCD())
                .subscribeOn(Schedulers.computation())
    }

    private fun createCD(): CD {
        val blockSongList = ArrayList<Volume>()
        blockSongList += getVolumeI()
        blockSongList += getVolumeII()
        return CD(title = resourceProvider.getString(R.string.title_cd_title), volumeList = blockSongList)
    }

    private fun getVolumeI(): Volume {
        val listSong = ArrayList<Song>()
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIFirstSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIFirstSongSubTitle),
                zodiacSign = ZodiacSign.Cancer(resourceProvider.getString(R.string.zodiacSign_cancer))
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeISecondSongTitle),
                subTitle = null,
                zodiacSign = ZodiacSign.Pisces(resourceProvider.getString(R.string.zodiacSign_pisces))
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIThirdSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIThirdSongSubTitle),
                zodiacSign = ZodiacSign.Taurus(resourceProvider.getString(R.string.zodiacSign_taurus))
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIFourthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIFourthSongSubTitle),
                zodiacSign = ZodiacSign.Capricorn(resourceProvider.getString(R.string.zodiacSign_capricorn))
        )

        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIFifthSongTitle),
                subTitle = null,
                zodiacSign = ZodiacSign.Scorpio(resourceProvider.getString(R.string.zodiacSign_scorpio))
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeISixthSongTitle),
                subTitle = null,
                zodiacSign = ZodiacSign.Sagittarius(resourceProvider.getString(R.string.zodiacSign_sagittarius))
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeISeventhSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeISeventhSongSubTitle),
                zodiacSign = ZodiacSign.Libra(resourceProvider.getString(R.string.zodiacSign_libra))
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIEighthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIEighthSongSubTitle),
                zodiacSign = ZodiacSign.Leo(resourceProvider.getString(R.string.zodiacSign_leo))
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeINinthSongTitle),
                subTitle = null,
                zodiacSign = ZodiacSign.Virgo(resourceProvider.getString(R.string.zodiacSign_virgo))
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeITenthSongTitle),
                subTitle = null,
                zodiacSign = ZodiacSign.Aries(resourceProvider.getString(R.string.zodiacSign_aries))
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIEleventhSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIEleventhSongSubTitle),
                zodiacSign = ZodiacSign.Gemini(resourceProvider.getString(R.string.zodiacSign_gemini))
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeITwelfthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeITwelfthSongSubTitle),
                zodiacSign = ZodiacSign.Aquarius(resourceProvider.getString(R.string.zodiacSign_aquarius))
        )
        return Volume(
                title = resourceProvider.getString(R.string.volumeITitle),
                songList = listSong
        )
    }

    private fun getVolumeII(): Volume {
        val listSong = ArrayList<Song>()
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIIFirstSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIIFirstSongSubTitle),
                zodiacSign = ZodiacSign.Cancer(resourceProvider.getString(R.string.zodiacSign_cancer))
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIISecondSongTitle),
                subTitle = null,
                zodiacSign = ZodiacSign.Sagittarius(resourceProvider.getString(R.string.zodiacSign_sagittarius))
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIIThirdSongTitle),
                subTitle = null,
                zodiacSign = ZodiacSign.Pisces(resourceProvider.getString(R.string.zodiacSign_pisces))
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIIFourthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIIFourthSongSubTitle),
                zodiacSign = ZodiacSign.Gemini(resourceProvider.getString(R.string.zodiacSign_gemini))
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIIFifthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIIFifthSongSubTitle),
                zodiacSign = ZodiacSign.Virgo(resourceProvider.getString(R.string.zodiacSign_virgo))
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIISixthSongTitle),
                subTitle = null,
                zodiacSign = ZodiacSign.Taurus(resourceProvider.getString(R.string.zodiacSign_taurus))
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIISeventhSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIISeventhSongSubTitle),
                zodiacSign = ZodiacSign.Scorpio(resourceProvider.getString(R.string.zodiacSign_scorpio))
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIIEighthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIIEighthSongSubTitle),
                zodiacSign = ZodiacSign.Aries(resourceProvider.getString(R.string.zodiacSign_aries))
        )

        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIINinthSongTitle),
                subTitle = null,
                zodiacSign = ZodiacSign.Libra(resourceProvider.getString(R.string.zodiacSign_libra))
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIITenthSongTitle),
                subTitle = null,
                zodiacSign = ZodiacSign.Aquarius(resourceProvider.getString(R.string.zodiacSign_aquarius))
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIIEleventhSongTitle),
                subTitle = null,
                zodiacSign = ZodiacSign.Leo(resourceProvider.getString(R.string.zodiacSign_leo))
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIITwelfthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIITwelfthSongSubTitle),
                zodiacSign = ZodiacSign.Capricorn(resourceProvider.getString(R.string.zodiacSign_capricorn))
        )
        return Volume(
                title = resourceProvider.getString(R.string.volumeII),
                songList = listSong
        )
    }
}