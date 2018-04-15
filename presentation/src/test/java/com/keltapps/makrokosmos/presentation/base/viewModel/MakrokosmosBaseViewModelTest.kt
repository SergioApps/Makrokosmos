package com.keltapps.makrokosmos.presentation.base.viewModel


import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

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
        viewModel.cleanUp()

        verify(compositeDisposable).clear()
    }
}