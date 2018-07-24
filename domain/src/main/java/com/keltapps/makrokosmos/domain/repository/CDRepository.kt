package com.keltapps.makrokosmos.domain.repository

import com.keltapps.makrokosmos.domain.entity.CD
import io.reactivex.Observable

interface CDRepository {
    fun getCD(): Observable<CD>
}