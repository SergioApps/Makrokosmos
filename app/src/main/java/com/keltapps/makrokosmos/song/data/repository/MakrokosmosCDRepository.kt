package com.keltapps.makrokosmos.song.data.repository

import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.base.resourceprovider.ResourceProvider
import com.keltapps.makrokosmos.song.domain.entity.*
import com.keltapps.makrokosmos.song.domain.repository.CDRepository
import io.reactivex.*
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class MakrokosmosCDRepository @Inject constructor(
        private val resourceProvider: ResourceProvider
) : CDRepository {

    private var cd: CD? = null

    override fun getSong(id: String): Single<Song> {
        return Single.fromCallable {
            getLocalCD().volumeList
                    .flatMap { it.songList }
                    .single { it.id == id }
        }
    }

    override fun getCD(): Observable<CD> {
        return Observable.fromCallable { getLocalCD() }
    }

    private fun getLocalCD(): CD {
        return cd ?: createCD()
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
                id = "1. Primeval Sounds (Genesis I) Cancer.mp3",
                title = resourceProvider.getString(R.string.volumeIFirstSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIFirstSongSubTitle),
                zodiacSign = ZodiacSign.Cancer(resourceProvider.getString(R.string.zodiacSign_cancer)),
                durationInSeconds = 255
        )
        listSong += Song(
                id = "2. Proteus (Pisces).mp3",
                title = resourceProvider.getString(R.string.volumeISecondSongTitle),
                subTitle = null,
                zodiacSign = ZodiacSign.Pisces(resourceProvider.getString(R.string.zodiacSign_pisces)),
                durationInSeconds = 82
        )
        listSong += Song(
                id = "3. Pastorale (from the Kingdom of Atlantis, ca. 10,000 B.C.) (Taurus).mp3",
                title = resourceProvider.getString(R.string.volumeIThirdSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIThirdSongSubTitle),
                zodiacSign = ZodiacSign.Taurus(resourceProvider.getString(R.string.zodiacSign_taurus)),
                durationInSeconds = 115
        )
        listSong += Song(
                id = "4. Crucifixus [SYMBOL] (Capricorn).mp3",
                title = resourceProvider.getString(R.string.volumeIFourthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIFourthSongSubTitle),
                zodiacSign = ZodiacSign.Capricorn(resourceProvider.getString(R.string.zodiacSign_capricorn)),
                durationInSeconds = 142
        )

        listSong += Song(
                id = "5. The Phantom Gondolier (Scorpio).mp3",
                title = resourceProvider.getString(R.string.volumeIFifthSongTitle),
                subTitle = null,
                zodiacSign = ZodiacSign.Scorpio(resourceProvider.getString(R.string.zodiacSign_scorpio)),
                durationInSeconds = 202
        )
        listSong += Song(
                id = "6. Night-Spell I (Sagittarius).mp3",
                title = resourceProvider.getString(R.string.volumeISixthSongTitle),
                subTitle = null,
                zodiacSign = ZodiacSign.Sagittarius(resourceProvider.getString(R.string.zodiacSign_sagittarius)),
                durationInSeconds = 224
        )
        listSong += Song(
                id = "7. Music of Shadows (for Aeolian Harp) (Libra).mp3",
                title = resourceProvider.getString(R.string.volumeISeventhSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeISeventhSongSubTitle),
                zodiacSign = ZodiacSign.Libra(resourceProvider.getString(R.string.zodiacSign_libra)),
                durationInSeconds = 133
        )
        listSong += Song(
                id = "8. The Magic Circle of Infinity (Moto Perpetuo) [SYMBOL] (Leo).mp3",
                title = resourceProvider.getString(R.string.volumeIEighthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIEighthSongSubTitle),
                zodiacSign = ZodiacSign.Leo(resourceProvider.getString(R.string.zodiacSign_leo)),
                durationInSeconds = 117
        )
        listSong += Song(
                id = "9. The Abyss of Time (Virgo).mp3",
                title = resourceProvider.getString(R.string.volumeINinthSongTitle),
                subTitle = null,
                zodiacSign = ZodiacSign.Virgo(resourceProvider.getString(R.string.zodiacSign_virgo)),
                durationInSeconds = 130
        )
        listSong += Song(
                id = "10. Spring-Fire (Aries).mp3",
                title = resourceProvider.getString(R.string.volumeITenthSongTitle),
                subTitle = null,
                zodiacSign = ZodiacSign.Aries(resourceProvider.getString(R.string.zodiacSign_aries)),
                durationInSeconds = 107
        )
        listSong += Song(
                id = "11. Dream Images (Love-Death Music) (Gemini).mp3",
                title = resourceProvider.getString(R.string.volumeIEleventhSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIEleventhSongSubTitle),
                zodiacSign = ZodiacSign.Gemini(resourceProvider.getString(R.string.zodiacSign_gemini)),
                durationInSeconds = 252
        )
        listSong += Song(
                id = "12. Spiral Galaxy [SYMBOL] (Aquarius).mp3",
                title = resourceProvider.getString(R.string.volumeITwelfthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeITwelfthSongSubTitle),
                zodiacSign = ZodiacSign.Aquarius(resourceProvider.getString(R.string.zodiacSign_aquarius)),
                durationInSeconds = 160
        )
        return Volume(
                title = resourceProvider.getString(R.string.volumeITitle),
                songList = listSong
        )
    }

    private fun getVolumeII(): Volume {
        val listSong = ArrayList<Song>()
        listSong += Song(
                id = "13. Morning Music (Genesis II) (Cancer).mp3",
                title = resourceProvider.getString(R.string.volumeIIFirstSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIIFirstSongSubTitle),
                zodiacSign = ZodiacSign.Cancer(resourceProvider.getString(R.string.zodiacSign_cancer)),
                durationInSeconds = 157
        )
        listSong += Song(
                id = "14. The Mystic Chord (Sagittarius).mp3",
                title = resourceProvider.getString(R.string.volumeIISecondSongTitle),
                subTitle = null,
                zodiacSign = ZodiacSign.Sagittarius(resourceProvider.getString(R.string.zodiacSign_sagittarius)),
                durationInSeconds = 138
        )
        listSong += Song(
                id = "15. Rain-Death Variations (Pisces).mp3",
                title = resourceProvider.getString(R.string.volumeIIThirdSongTitle),
                subTitle = null,
                zodiacSign = ZodiacSign.Pisces(resourceProvider.getString(R.string.zodiacSign_pisces)),
                durationInSeconds = 93
        )
        listSong += Song(
                id = "16. Twin Suns (DoppelgÑnger aus der Ewigkeit) [SYMBOL] (Gemini).mp3",
                title = resourceProvider.getString(R.string.volumeIIFourthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIIFourthSongSubTitle),
                zodiacSign = ZodiacSign.Gemini(resourceProvider.getString(R.string.zodiacSign_gemini)),
                durationInSeconds = 184
        )
        listSong += Song(
                id = "17. Ghost-Nocturne for the Druids of Stonehenge (Night-Spell II) (Virgo).mp3",
                title = resourceProvider.getString(R.string.volumeIIFifthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIIFifthSongSubTitle),
                zodiacSign = ZodiacSign.Virgo(resourceProvider.getString(R.string.zodiacSign_virgo)),
                durationInSeconds = 96
        )
        listSong += Song(
                id = "18. Gargoyles (Taurus).mp3",
                title = resourceProvider.getString(R.string.volumeIISixthSongTitle),
                subTitle = null,
                zodiacSign = ZodiacSign.Taurus(resourceProvider.getString(R.string.zodiacSign_taurus)),
                durationInSeconds = 68
        )
        listSong += Song(
                id = "19. Tora! Tora! Tora! (Cadenza Apocalittica) (Scorpio).mp3",
                title = resourceProvider.getString(R.string.volumeIISeventhSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIISeventhSongSubTitle),
                zodiacSign = ZodiacSign.Scorpio(resourceProvider.getString(R.string.zodiacSign_scorpio)),
                durationInSeconds = 119
        )
        listSong += Song(
                id = "20. A Prophecy of Nostradamus [SYMBOL] (Aries).mp3",
                title = resourceProvider.getString(R.string.volumeIIEighthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIIEighthSongSubTitle),
                zodiacSign = ZodiacSign.Aries(resourceProvider.getString(R.string.zodiacSign_aries)),
                durationInSeconds = 201
        )

        listSong += Song(
                id = "21. Cosmic wind (Libra).mp3",
                title = resourceProvider.getString(R.string.volumeIINinthSongTitle),
                subTitle = null,
                zodiacSign = ZodiacSign.Libra(resourceProvider.getString(R.string.zodiacSign_libra)),
                durationInSeconds = 142
        )
        listSong += Song(
                id = "22. Voices from Corona Borealis (Aquarius).mp3",
                title = resourceProvider.getString(R.string.volumeIITenthSongTitle),
                subTitle = null,
                zodiacSign = ZodiacSign.Aquarius(resourceProvider.getString(R.string.zodiacSign_aquarius)),
                durationInSeconds = 193
        )
        listSong += Song(
                id = "23. Litany of the Galactic Bells (Leo).mp3",
                title = resourceProvider.getString(R.string.volumeIIEleventhSongTitle),
                subTitle = null,
                zodiacSign = ZodiacSign.Leo(resourceProvider.getString(R.string.zodiacSign_leo)),
                durationInSeconds = 158
        )
        listSong += Song(
                id = "24. Agnus Dei [SYMBOL] (Capricorn).mp3",
                title = resourceProvider.getString(R.string.volumeIITwelfthSongTitle),
                subTitle = resourceProvider.getString(R.string.volumeIITwelfthSongSubTitle),
                zodiacSign = ZodiacSign.Capricorn(resourceProvider.getString(R.string.zodiacSign_capricorn)),
                durationInSeconds = 209
        )
        return Volume(
                title = resourceProvider.getString(R.string.volumeII),
                songList = listSong
        )
    }
}