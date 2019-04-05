package es.jarroyo.books.ui.viewmodel.books

import es.jarroyo.books.domain.model.Response
import es.jarroyo.books.domain.model.books.BooksListResponse


sealed class GetBooksListState {
    abstract val response: Response<BooksListResponse>?
}
data class SuccessGetBooksListState(override val response: Response<BooksListResponse>) : GetBooksListState()
data class LoadingGetBooksListState(override val response: Response<BooksListResponse>? = null) : GetBooksListState()
data class ErrorGetBooksListState(override val response: Response<BooksListResponse>) : GetBooksListState()