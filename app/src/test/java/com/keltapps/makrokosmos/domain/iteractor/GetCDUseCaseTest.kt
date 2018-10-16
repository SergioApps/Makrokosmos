package com.keltapps.makrokosmos.domain.iteractor

import com.keltapps.makrokosmos.song.domain.entity.CD
import com.keltapps.makrokosmos.song.domain.iteractor.GetCDUseCase
import com.keltapps.makrokosmos.song.domain.repository.CDRepository
import io.reactivex.Observable
import org.junit.*
import org.mockito.*
import org.mockito.Mockito.`when`

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
    fun execute_should_returnCD_when_repositoryCallIsSuccessful() {
        `when`(repository.getCD()).thenReturn(Observable.just(cd))

        val testObserver = useCase.execute().test()

        testObserver.assertValue { it == cd }
    }
}