package com.keltapps.musicalzodiacpiano.info.domain.iteractor

import com.keltapps.musicalzodiacpiano.info.domain.entity.Info
import com.keltapps.musicalzodiacpiano.info.domain.repository.InfoRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetAuthorInfoUseCase @Inject constructor(
        private val repository: InfoRepository
) {

    fun execute(): Observable<Info> = repository.getAuthorInfo()
}