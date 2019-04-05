package es.jarroyo.books.domain.usecase.books.getBooksList

import es.jarroyo.books.domain.usecase.base.BaseRequest

class GetBooksListRequest(var query: String) : BaseRequest {
    override fun validate(): Boolean {
        return true
    }
}