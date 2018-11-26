package com.keltapps.musicalzodiacpiano.info.domain.iteractor

import com.keltapps.musicalzodiacpiano.info.domain.entity.Info
import com.keltapps.musicalzodiacpiano.info.domain.repository.InfoRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetInterpreterInfoUseCaseTest {

    private lateinit var infoUseCase: GetInterpreterInfoUseCase

    @Mock
    private lateinit var repository: InfoRepository

    @Mock
    private lateinit var mockInfo: Info

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        infoUseCase = GetInterpreterInfoUseCase(repository)
    }

    @Test
    fun execute_should_returnCD_when_repositoryCallIsSuccessful() {
        `when`(repository.getInterpreterInfo()).thenReturn(Observable.just(mockInfo))

        val testObserver = infoUseCase.execute().test()

        testObserver.assertValue { it == mockInfo }
    }
}