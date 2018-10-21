package com.keltapps.makrokosmos.info.domain.iteractor

import com.keltapps.makrokosmos.info.domain.entity.Info
import com.keltapps.makrokosmos.info.domain.repository.InfoRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetAuthorInfoUseCaseTest {

    private lateinit var useCase: GetAuthorInfoUseCase

    @Mock
    private lateinit var repository: InfoRepository

    @Mock
    private lateinit var mockInfo: Info

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = GetAuthorInfoUseCase(repository)
    }

    @Test
    fun execute_should_returnCD_when_repositoryCallIsSuccessful() {
        `when`(repository.getAuthorInfo()).thenReturn(Observable.just(mockInfo))

        val testObserver = useCase.execute().test()

        testObserver.assertValue { it == mockInfo }
    }
}