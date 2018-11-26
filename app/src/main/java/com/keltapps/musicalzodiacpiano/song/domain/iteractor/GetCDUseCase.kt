package com.keltapps.musicalzodiacpiano.song.domain.iteractor

import com.keltapps.musicalzodiacpiano.song.domain.entity.CD
import com.keltapps.musicalzodiacpiano.song.domain.repository.CDRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetCDUseCase @Inject constructor(
        private val repository: CDRepository
) {

    fun execute(): Observable<CD> = repository.getCD()
}