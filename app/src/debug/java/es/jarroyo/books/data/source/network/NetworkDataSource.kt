package es.jarroyo.books.data.source.network

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.microhealth.lmc.utils.NetworkSystemAbstract
import es.jarroyo.books.BuildConfig
import es.jarroyo.books.domain.model.Response
import es.jarroyo.books.domain.model.books.BooksListResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class NetworkDataSource(context: Context, private val networkSystem: NetworkSystemAbstract) : INetworkDataSource(networkSystem) {

    /**
     * GET BOOKS LIST
     */
    override suspend fun getBooksList(query: String): Response<BooksListResponse> {
        val googleBooksAPI = initRetrofitGoogleBooksAPI()
        try {
            val getBooksList =
                googleBooksAPI.getBooksList(query)
                    .await()

            return Response.Success(getBooksList)
        } catch (e: Exception) {
            return Response.Error(e)
        }
    }


    private fun initRetrofitGoogleBooksAPI(): GoogleBooksAPI {
        val retrofit = Retrofit.Builder().apply {
            baseUrl(BuildConfig.GOOGLE_API_BOOKS_URL_BASE)
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(CoroutineCallAdapterFactory())
        }.build()

        val googleBooksAPI = retrofit.create(GoogleBooksAPI::class.java)
        return googleBooksAPI
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