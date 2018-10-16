package com.keltapps.makrokosmos.song.domain.repository

import com.keltapps.makrokosmos.song.domain.entity.CD
import io.reactivex.Observable

interface CDRepository {
    fun getCD(): Observable<CD>
}