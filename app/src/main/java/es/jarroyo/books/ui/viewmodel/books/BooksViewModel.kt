package es.jarroyo.books.ui.viewmodel.books

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.jarroyo.books.domain.model.Response
import es.jarroyo.books.domain.model.books.BooksListResponse
import es.jarroyo.books.domain.usecase.books.getBooksList.GetBooksListRequest
import es.jarroyo.books.domain.usecase.books.getBooksList.GetBooksListUseCase
import es.jarroyo.books.utils.launchSilent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class BooksViewModel
    @Inject
    constructor(private val getBooksListUseCase: GetBooksListUseCase,
                private val coroutineContext: CoroutineContext)
    : ViewModel() {

    private var job: Job = Job()

    var getBooksListStateLiveData = MutableLiveData<GetBooksListState>()

    init {
    }

    /**
     * GET CURRENT LOCATION
     */
    fun getBooksList(query: String) = launchSilent(coroutineContext, job) {
        getBooksListStateLiveData.postValue(LoadingGetBooksListState())

        val request = GetBooksListRequest(query)
        val response = getBooksListUseCase.execute(request)
        processCurrentLocationResponse(response)
    }

    fun processCurrentLocationResponse(response: Response<BooksListResponse>){
        if (response is Response.Success) {
            getBooksListStateLiveData.postValue(
                SuccessGetBooksListState(
                    response
                )
            )
        } else if (response is Response.Error) {
            getBooksListStateLiveData.postValue(
                ErrorGetBooksListState(
                    response
                )
            )
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}