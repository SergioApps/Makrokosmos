package com.keltapps.makrokosmos.song.domain.iteractor

import com.keltapps.makrokosmos.song.domain.entity.CD
import com.keltapps.makrokosmos.song.domain.repository.CDRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetCDUseCase @Inject constructor(
        private val repository: CDRepository
) {

    fun execute(): Observable<CD> = repository.getCD()
}