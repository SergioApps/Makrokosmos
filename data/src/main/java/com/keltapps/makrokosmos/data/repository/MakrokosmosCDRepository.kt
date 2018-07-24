package com.keltapps.makrokosmos.data.repository

import com.keltapps.makrokosmos.data.R
import com.keltapps.makrokosmos.data.resourceProvider.ResourceProvider
import com.keltapps.makrokosmos.domain.entity.*
import com.keltapps.makrokosmos.domain.repository.CDRepository
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
                element = Element.Water
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeISecondSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeISecondSongSubTitle),
                element = Element.Water
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIThirdSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIThirdSongSubTitle),
                element = Element.Earth
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIFourthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIFourthSongSubTitle),
                element = Element.Earth
        )

        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIFifthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIFifthSongSubTitle),
                element = Element.Water
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeISixthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeISixthSongSubTitle),
                element = Element.Fire
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeISeventhSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeISeventhSongSubTitle),
                element = Element.Air
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIEighthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIEighthSongSubTitle),
                element = Element.Fire
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeINinthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeINinthSongSubTitle),
                element = Element.Earth
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeITenthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeITenthSongSubTitle),
                element = Element.Fire
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIEleventhSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIEleventhSongSubTitle),
                element = Element.Air
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeITwelfthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeITwelfthSongSubTitle),
                element = Element.Air
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
                element = Element.Water
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIISecondSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIISecondSongSubTitle),
                element = Element.Fire
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIIThirdSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIIThirdSongSubTitle),
                element = Element.Water
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIIFourthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIIFourthSongSubTitle),
                element = Element.Air
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIIFifthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIIFifthSongSubTitle),
                element = Element.Earth
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIISixthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIISixthSongSubTitle),
                element = Element.Earth
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIISeventhSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIISeventhSongSubTitle),
                element = Element.Water
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIIEighthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIIEighthSongSubTitle),
                element = Element.Fire
        )

        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIINinthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIINinthSongSubTitle),
                element = Element.Air
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIITenthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIITenthSongSubTitle),
                element = Element.Air
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIIEleventhSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIIEleventhSongSubTitle),
                element = Element.Fire
        )
        listSong += Song(
                title = resourceProvider.getString(R.string.volumeIITwelfthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIITwelfthSongSubTitle),
                element = Element.Earth
        )
        return Volume(
                title = resourceProvider.getString(R.string.volumeII),
                songList = listSong
        )
    }
}