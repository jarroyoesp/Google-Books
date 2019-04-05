package es.jarroyo.books.domain.usecase.books.getBooksList

import es.jarroyo.books.data.repository.BooksRepository
import es.jarroyo.books.domain.model.Response
import es.jarroyo.books.domain.model.books.BooksListResponse
import es.jarroyo.books.domain.usecase.base.BaseUseCase

open class GetBooksListUseCase(val repository: BooksRepository) : BaseUseCase<GetBooksListRequest, BooksListResponse>() {

    override suspend fun run(): Response<BooksListResponse> {
        return repository.getBooksList(request!!)
    }
}