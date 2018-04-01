package com.keltapps.makrokosmos.domain.repository

import com.keltapps.makrokosmos.domain.entity.CD
import com.keltapps.makrokosmos.domain.model.RepositoryModel
import io.reactivex.Observable

interface CDRepository {
    fun getCD(): Observable<RepositoryModel<CD>>
}