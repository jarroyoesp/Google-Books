package es.jarroyo.books.data.source.network

import com.microhealth.lmc.utils.NetworkSystemAbstract
import es.jarroyo.books.domain.model.Response
import es.jarroyo.books.domain.model.currentWeather.CurrentWeather
import es.jarroyo.books.domain.model.currentWeather.CurrentWeatherFactory
import es.jarroyo.books.domain.model.forecast.Forecast
import es.jarroyo.books.domain.model.forecast.ForecastFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.io.IOException


class NetworkDataSource(private val networkSystem: NetworkSystemAbstract) : INetworkDataSource(networkSystem) {

    /**
     * GET FORECAST
     */
    override suspend fun getForecast(cityName: String): Response<Forecast> {
        val forescast = ForecastFactory.createForecastTest()
        return Response.Success(forescast)
    }

    /**
     * GET CURRENT WEATHER BY ID
     */
    override suspend fun getCurrentWeatherByName(cityName: String): Response<CurrentWeather> {
        val currentWeather = CurrentWeatherFactory.createCurrentWeatherTest()
        return Response.Success(currentWeather)
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