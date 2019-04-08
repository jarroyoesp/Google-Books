package es.jarroyo.books.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.whenever
import es.jarroyo.books.data.factory.BooksListFactory
import es.jarroyo.books.domain.model.Response
import es.jarroyo.books.domain.usecase.books.getBooksList.GetBooksListRequest
import es.jarroyo.books.domain.usecase.books.getBooksList.GetBooksListUseCase
import es.jarroyo.books.ui.viewmodel.books.BooksViewModel
import es.jarroyo.books.ui.viewmodel.books.GetBooksListState
import es.jarroyo.books.ui.viewmodel.books.LoadingGetBooksListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import kotlin.coroutines.CoroutineContext



class BooksViewModelTest {

    @get: Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit  var viewModel : BooksViewModel

    var coroutineContext: CoroutineContext = Dispatchers.Unconfined

    @Mock
    lateinit var getBooksListUseCase: GetBooksListUseCase

    @Mock
    lateinit var observer: Observer<GetBooksListState>

    @Mock
    lateinit var lifeCycleOwner: LifecycleOwner

    lateinit var lifeCycle: LifecycleRegistry


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        prepareViewModel()

        lifeCycle = LifecycleRegistry(lifeCycleOwner)
        `when` (lifeCycleOwner.lifecycle).thenReturn(lifeCycle)
        lifeCycle.handleLifecycleEvent(Lifecycle.Event.ON_START)

    }

    /**
     * Verify that "getBookListUseCase" is executed once when call getCityCurrentWeather()
     */
    @Test
    fun `should request the current weather when call getCityCurrentWeather()`() {
        runBlocking {
            val response = Response.Success(BooksListFactory.createBooksListResponse1())
            val request = GetBooksListRequest("query")
            whenever(getBooksListUseCase.execute(request)).thenReturn(response)

            viewModel.getBooksList("query")
            Mockito.verify(getBooksListUseCase, Mockito.times(1)).execute()
        }
    }

    /**
     * Verify Loading State is set when make request to get data
     */
    @Test
    fun `should loading state when make request`() {
        runBlocking {
            val response = Response.Success(BooksListFactory.createBooksListResponse1())
            val request = GetBooksListRequest("query")
            whenever(getBooksListUseCase.execute(request)).thenReturn(response)

            viewModel.getBooksList("Query")

            assertThat(viewModel.getBooksListStateLiveData.value, instanceOf(LoadingGetBooksListState::class.java))
        }
    }


    private fun prepareViewModel(){
        viewModel = BooksViewModel(getBooksListUseCase, coroutineContext)
    }
}