package es.jarroyo.books.data.source.network

import android.content.Context
import com.microhealth.lmc.utils.NetworkSystemAbstract
import es.jarroyo.books.data.factory.BooksListFactory
import es.jarroyo.books.domain.model.Response
import es.jarroyo.books.domain.model.books.BooksListResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.io.IOException


class NetworkDataSource(private val context: Context,
                        private val networkSystem: NetworkSystemAbstract) : INetworkDataSource(networkSystem) {
    /**
     * GET BOOKS LIST
     */
    override suspend fun getBooksList(query: String): Response<BooksListResponse> {
        val response = BooksListFactory.createBooksListResponse1(context)
        return Response.Success(response)
    }


    var okHttpClient = OkHttpClient.Builder()
        .addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                val request = chain.request()
                val response = chain.proceed(request)

                // todo deal with the issues the way you need to
                if (response.code() == 500) {
                    return response
                }

                return response
            }
        })
        .build()

}