package com.keltapps.musicalzodiacpiano.song.domain.iteractor

import com.keltapps.musicalzodiacpiano.audio.client.domain.repository.AudioRepository
import com.keltapps.musicalzodiacpiano.song.domain.entity.Song
import com.keltapps.musicalzodiacpiano.song.domain.repository.CDRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetSongPlayingUseCase @Inject constructor(
        private val audioRepository: AudioRepository,
        private val cdRepository: CDRepository
) {

    fun execute(): Observable<Song> {
        return audioRepository.getSongIdPlaying()
                .flatMap { cdRepository.getSong(it).toObservable() }
    }
}