package com.keltapps.musicalzodiacpiano.song.domain.repository

import com.keltapps.musicalzodiacpiano.song.domain.entity.*
import io.reactivex.*

interface CDRepository {
    fun getCD(): Observable<CD>
    fun getSong(id: String): Single<Song>
}