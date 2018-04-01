package com.keltapps.makrokosmos.domain.model

data class RepositoryModel<out T>(val data: T? = null,
                                  val errorMessage: String = "") {

    fun isSuccessful(): Boolean = errorMessage.isEmpty()
}