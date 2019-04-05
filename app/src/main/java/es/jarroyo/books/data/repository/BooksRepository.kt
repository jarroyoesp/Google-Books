package es.jarroyo.books.data.repository

import es.jarroyo.books.data.source.network.INetworkDataSource
import es.jarroyo.books.domain.model.Response
import es.jarroyo.books.domain.model.books.BooksListResponse
import es.jarroyo.books.domain.usecase.books.getBooksList.GetBooksListRequest

class BooksRepository(
    private val networkDataSource: INetworkDataSource
) {

    val TAG = BooksRepository::class.java.simpleName

    /***********************************************************************************************
     * GET BOOKS LIST
     **********************************************************************************************/
    suspend fun getBooksList(request: GetBooksListRequest): Response<BooksListResponse> {
        return networkDataSource.getBooksList(request.query)
    }
}