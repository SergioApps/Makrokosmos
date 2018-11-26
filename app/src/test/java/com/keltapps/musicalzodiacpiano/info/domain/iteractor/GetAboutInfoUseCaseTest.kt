package com.keltapps.musicalzodiacpiano.info.domain.iteractor

import com.keltapps.musicalzodiacpiano.info.domain.entity.Info
import com.keltapps.musicalzodiacpiano.info.domain.repository.InfoRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetAboutInfoUseCaseTest {

    private lateinit var useCase: GetAboutInfoUseCase

    @Mock
    private lateinit var repository: InfoRepository

    @Mock
    private lateinit var mockInfo: Info

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = GetAboutInfoUseCase(repository)
    }

    @Test
    fun execute_should_returnCD_when_repositoryCallIsSuccessful() {
        `when`(repository.getAboutInfo()).thenReturn(Observable.just(mockInfo))

        val testObserver = useCase.execute().test()

        testObserver.assertValue { it == mockInfo }
    }
}