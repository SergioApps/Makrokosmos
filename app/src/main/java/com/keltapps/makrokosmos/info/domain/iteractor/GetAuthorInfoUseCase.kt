package com.keltapps.makrokosmos.info.domain.iteractor

import com.keltapps.makrokosmos.info.domain.entity.Info
import com.keltapps.makrokosmos.info.domain.repository.InfoRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetAuthorInfoUseCase @Inject constructor(
        private val repository: InfoRepository
) {

    fun execute(): Observable<Info> = repository.getAuthorInfo()
}