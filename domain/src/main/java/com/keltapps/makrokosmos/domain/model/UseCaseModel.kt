package com.keltapps.makrokosmos.domain.model

data class UseCaseModel<out T>(val data: T,
                               val errorMessage: String = "")