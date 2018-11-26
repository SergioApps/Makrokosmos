package com.keltapps.musicalzodiacpiano.info.domain.repository

import com.keltapps.musicalzodiacpiano.info.domain.entity.Info
import io.reactivex.Observable

interface InfoRepository {
    fun getAboutInfo(): Observable<Info>
    fun getAuthorInfo(): Observable<Info>
    fun getInterpreterInfo(): Observable<Info>
}