package com.keltapps.makrokosmos.domain.iteractor

import com.google.common.truth.Truth.assertThat
import com.keltapps.makrokosmos.domain.entity.CD
import com.keltapps.makrokosmos.domain.model.RepositoryModel
import com.keltapps.makrokosmos.domain.repository.CDRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetCDUseCaseTest {

    private lateinit var useCase: GetCDUseCase

    @Mock
    private lateinit var repository: CDRepository

    private val cd = CD(emptyList(), "title")

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = GetCDUseCase(repository)
    }

    @Test
    fun execute_should_returnCDUseCaseModelWithData_when_repositoryCallIsSuccessful() {
        val repositoryModel = RepositoryModel(data = cd)
        `when`(repository.getCD()).thenReturn(Observable.just(repositoryModel))

        val testObserver = useCase.execute().test()

        val result = testObserver.values()[0]
        assertThat(result.data).isEqualTo(cd)
        assertThat(result.errorMessage).isEqualTo(repositoryModel.errorMessage)
    }

    @Test
    fun execute_should_returnCDUseCaseModelWithError_when_repositoryCallIsUnsuccessful() {
        val repositoryModel = RepositoryModel<CD>(errorMessage = "error message")
        `when`(repository.getCD()).thenReturn(Observable.just(repositoryModel))

        val testObserver = useCase.execute().test()

        val result = testObserver.values()[0]
        assertThat(result.data.title).isEmpty()
        assertThat(result.data.blockSongList).isEmpty()
        assertThat(result.errorMessage).isEqualTo(repositoryModel.errorMessage)
    }
}