package com.keltapps.makrokosmos.domain.iteractor

import com.keltapps.makrokosmos.domain.entity.CD
import com.keltapps.makrokosmos.domain.model.RepositoryModel
import com.keltapps.makrokosmos.domain.model.UseCaseModel
import com.keltapps.makrokosmos.domain.repository.CDRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetCDUseCase @Inject constructor(private val repository: CDRepository) {

    fun execute(): Observable<UseCaseModel<CD>> {
        return repository.getCD().map {
            UseCaseModel(it.data ?: CD(emptyList(), ""), it.errorMessage)
        }
    }
}