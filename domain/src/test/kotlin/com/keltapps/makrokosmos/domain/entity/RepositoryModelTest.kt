package com.keltapps.makrokosmos.domain.entity


import com.google.common.truth.Truth.assertThat
import com.keltapps.makrokosmos.domain.model.RepositoryModel
import org.junit.Test

class RepositoryModelTest {

    @Test
    fun isSuccessful_should_returnTrue_when_messageIsNull() {
        val repositoryModel = RepositoryModel(data = Unit)

        val result = repositoryModel.isSuccessful()

        assertThat(result).isTrue()
    }

    @Test
    fun isSuccessful_should_returnFalse_when_messageIsNotNull() {
        val repositoryModel = RepositoryModel(data = Unit, errorMessage = "error message")

        val result = repositoryModel.isSuccessful()

        assertThat(result).isFalse()
    }
}