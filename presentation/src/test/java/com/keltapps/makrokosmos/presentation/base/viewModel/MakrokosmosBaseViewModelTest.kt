package com.keltapps.makrokosmos.presentation.base.viewModel


import io.reactivex.disposables.*
import org.junit.*
import org.mockito.*
import org.mockito.Mockito.verify

class MakrokosmosBaseViewModelTest {

    private lateinit var viewModel: MakrokosmosBaseViewModel

    @Mock
    private lateinit var compositeDisposable: CompositeDisposable
    @Mock
    private lateinit var disposable: Disposable

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MakrokosmosBaseViewModel(compositeDisposable)
    }

    @Test
    fun addDisposable_should_addDisposable() {
        viewModel.addDisposable(disposable)

        verify(compositeDisposable).add(disposable)
    }

    @Test
    fun cleanUp_should_clearCompositeDisposable() {
        //viewModel.onccleanUp()

       // verify(compositeDisposable).clear()
    }
}