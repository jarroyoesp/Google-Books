package es.jarroyo.books.data.source.network

import es.jarroyo.books.BuildConfig
import es.jarroyo.books.domain.model.books.BooksListResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBooksAPI {
    companion object {
        // PATH
        const val URL_PATH_BOOKS_VOLUME = "books/v1/volumes"
        const val URL_PATH_CURRENT_FORECAST = "/data/2.5/forecast"

        // PARAMETERS
        const val URL_PARAMETER_QUERY = "q"
        const val URL_PARAMETER_MAX_RESULTS = "maxResults"
        const val URL_PARAMETER_CITY_NAME = "q"
        const val URL_PARAMETER_API_KEY = "key"
        const val URL_PARAMETER_UNITS = "units"
    }

    /**
     * CURRENT WEATHER
     */
    @GET(GoogleBooksAPI.URL_PATH_BOOKS_VOLUME)
    fun getBooksList(
        @Query(URL_PARAMETER_QUERY) query: String = "",
        @Query(URL_PARAMETER_API_KEY) apiKey: String = BuildConfig.GOOGLE_API_KEY,
        @Query(URL_PARAMETER_MAX_RESULTS) maxResults: Int = 40
    ): Deferred<BooksListResponse>


}

