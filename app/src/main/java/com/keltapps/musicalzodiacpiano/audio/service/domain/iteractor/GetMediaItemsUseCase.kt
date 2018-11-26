package com.keltapps.musicalzodiacpiano.audio.service.domain.iteractor

import android.support.v4.media.MediaBrowserCompat
import com.keltapps.musicalzodiacpiano.audio.service.domain.repository.MusicLibraryRepository
import com.keltapps.musicalzodiacpiano.song.domain.repository.CDRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class GetMediaItemsUseCase @Inject constructor(
        private val cdRepository: CDRepository,
        private val musicLibraryRepository: MusicLibraryRepository
) {

    fun execute(): Single<List<MediaBrowserCompat.MediaItem>> {
        return cdRepository.getCD()
                .flatMap { Observable.just(musicLibraryRepository.getMediaItems(it)) }
                .firstOrError()
    }
}