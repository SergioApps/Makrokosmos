package com.keltapps.makrokosmos.song.domain.repository

import com.keltapps.makrokosmos.song.domain.entity.*
import io.reactivex.*

interface CDRepository {
    fun getCD(): Observable<CD>
    fun getSong(id: String): Single<Song>
}