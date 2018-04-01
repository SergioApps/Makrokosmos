package com.keltapps.makrokosmos.data.repository

import com.keltapps.makrokosmos.data.R
import com.keltapps.makrokosmos.data.resourceProvider.ResourceProvider
import com.keltapps.makrokosmos.domain.entity.BlockSong
import com.keltapps.makrokosmos.domain.entity.CD
import com.keltapps.makrokosmos.domain.model.RepositoryModel
import com.keltapps.makrokosmos.domain.entity.Song
import com.keltapps.makrokosmos.domain.repository.CDRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class MakrokosmosCDRepository(private val resourceProvider: ResourceProvider) : CDRepository {

    override fun getCD(): Observable<RepositoryModel<CD>> {
        val repositoryModel = RepositoryModel(createCD())
        return Observable.just(repositoryModel).subscribeOn(Schedulers.computation())
    }

    private fun createCD(): CD {
        val blockSongList = ArrayList<BlockSong>()
        blockSongList += getVolumeIPart1()
        blockSongList += getVolumeIPart2()
        blockSongList += getVolumeIPart3()
        blockSongList += getVolumeIIPart1()
        blockSongList += getVolumeIIPart2()
        blockSongList += getVolumeIIPart3()
        return CD(title = resourceProvider.getString(R.string.title_cd_title), blockSongList = blockSongList)
    }

    private fun getVolumeIPart1(): BlockSong {
        val listSong = ArrayList<Song>()
        listSong += Song(title = resourceProvider.getString(R.string.volumeIFirstSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIFirstSongSubTitle))
        listSong += Song(title = resourceProvider.getString(R.string.volumeISecondSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeISecondSongSubTitle))
        listSong += Song(title = resourceProvider.getString(R.string.volumeIThirdSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIThirdSongSubTitle))
        listSong += Song(title = resourceProvider.getString(R.string.volumeIFourthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIFourthSongSubTitle))
        return BlockSong(title = resourceProvider.getString(R.string.volumeIPart1Title),
                songList = listSong)
    }

    private fun getVolumeIPart2(): BlockSong {
        val listSong = ArrayList<Song>()
        listSong += Song(title = resourceProvider.getString(R.string.volumeIFifthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIFifthSongSubTitle))
        listSong += Song(title = resourceProvider.getString(R.string.volumeISixthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeISixthSongSubTitle))
        listSong += Song(title = resourceProvider.getString(R.string.volumeISeventhSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeISeventhSongSubTitle))
        listSong += Song(title = resourceProvider.getString(R.string.volumeIEighthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIEighthSongSubTitle))
        return BlockSong(title = resourceProvider.getString(R.string.volumeIPart2Title),
                songList = listSong)
    }

    private fun getVolumeIPart3(): BlockSong {
        val listSong = ArrayList<Song>()
        listSong += Song(title = resourceProvider.getString(R.string.volumeINinthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeINinthSongSubTitle))
        listSong += Song(title = resourceProvider.getString(R.string.volumeITenthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeITenthSongSubTitle))
        listSong += Song(title = resourceProvider.getString(R.string.volumeIEleventhSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIEleventhSongSubTitle))
        listSong += Song(title = resourceProvider.getString(R.string.volumeITwelfthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeITwelfthSongSubTitle))
        return BlockSong(title = resourceProvider.getString(R.string.volumeIPart3Title),
                songList = listSong)
    }

    private fun getVolumeIIPart1(): BlockSong {
        val listSong = ArrayList<Song>()
        listSong += Song(title = resourceProvider.getString(R.string.volumeIIFirstSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIIFirstSongSubTitle))
        listSong += Song(title = resourceProvider.getString(R.string.volumeIISecondSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIISecondSongSubTitle))
        listSong += Song(title = resourceProvider.getString(R.string.volumeIIThirdSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIIThirdSongSubTitle))
        listSong += Song(title = resourceProvider.getString(R.string.volumeIIFourthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIIFourthSongSubTitle))
        return BlockSong(title = resourceProvider.getString(R.string.volumeIIPart1Title),
                songList = listSong)
    }

    private fun getVolumeIIPart2(): BlockSong {
        val listSong = ArrayList<Song>()
        listSong += Song(title = resourceProvider.getString(R.string.volumeIIFifthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIIFifthSongSubTitle))
        listSong += Song(title = resourceProvider.getString(R.string.volumeIISixthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIISixthSongSubTitle))
        listSong += Song(title = resourceProvider.getString(R.string.volumeIISeventhSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIISeventhSongSubTitle))
        listSong += Song(title = resourceProvider.getString(R.string.volumeIIEighthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIIEighthSongSubTitle))
        return BlockSong(title = resourceProvider.getString(R.string.volumeIIPart2Title),
                songList = listSong)
    }

    private fun getVolumeIIPart3(): BlockSong {
        val listSong = ArrayList<Song>()
        listSong += Song(title = resourceProvider.getString(R.string.volumeIINinthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIINinthSongSubTitle))
        listSong += Song(title = resourceProvider.getString(R.string.volumeIITenthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIITenthSongSubTitle))
        listSong += Song(title = resourceProvider.getString(R.string.volumeIIEleventhSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIIEleventhSongSubTitle))
        listSong += Song(title = resourceProvider.getString(R.string.volumeIITwelfthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIITwelfthSongSubTitle))
        return BlockSong(title = resourceProvider.getString(R.string.volumeIIPart3Title),
                songList = listSong)
    }
}