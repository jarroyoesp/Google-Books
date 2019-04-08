package es.jarroyo.books.data.source.network

import es.jarroyo.books.domain.model.Response
import es.jarroyo.books.domain.model.books.BooksListResponse
import es.jarroyo.books.utils.NetworkSystemAbstract

open abstract class INetworkDataSource(private val networkSystem: NetworkSystemAbstract) {

     /**
     * BOOKS LIST
     */
    abstract suspend fun getBooksList(query: String): Response<BooksListResponse>


}