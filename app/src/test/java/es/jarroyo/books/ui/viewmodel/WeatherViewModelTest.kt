package es.jarroyo.books.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.whenever
import es.jarroyo.books.data.exception.NetworkConnectionException
import es.jarroyo.books.domain.model.Response
import es.jarroyo.books.domain.model.currentWeather.CurrentWeatherFactory
import es.jarroyo.books.domain.usecase.currentWeather.GetCurrentWeatherByNameRequest
import es.jarroyo.books.domain.usecase.currentWeather.GetCurrentWeatherByNameUseCase
import es.jarroyo.books.domain.usecase.currentWeather.getWeatherList.GetWeatherListUseCase
import es.jarroyo.books.domain.usecase.location.currentLocation.GetCurrentLocationUseCase
import es.jarroyo.books.ui.viewmodel.model.weather.CurrentWeatherState
import es.jarroyo.books.ui.viewmodel.model.weather.DefaultCurrentWeatherState
import es.jarroyo.books.ui.viewmodel.model.weather.ErrorCurrentWeatherState
import es.jarroyo.books.ui.viewmodel.model.weather.LoadingCurrentWeatherState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import kotlin.coroutines.CoroutineContext



class WeatherViewModelTest {

    @get: Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit  var viewModel : WeatherViewModel

    var coroutineContext: CoroutineContext = Dispatchers.Unconfined

    @Mock
    lateinit var getCurrentWeatherByNameUseCase: GetCurrentWeatherByNameUseCase

    @Mock
    lateinit var getCurrentLocationUseCase: GetCurrentLocationUseCase

    @Mock
    lateinit var getWeatherListUseCase: GetWeatherListUseCase

    @Mock
    lateinit var observer: Observer<CurrentWeatherState>

    @Mock
    lateinit var lifeCycleOwner: LifecycleOwner

    lateinit var lifeCycle: LifecycleRegistry

    // CONSTANT DATA
    private val CITY_NAME = "Zaragoza"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        prepareViewModel()

        lifeCycle = LifecycleRegistry(lifeCycleOwner)
        `when` (lifeCycleOwner.lifecycle).thenReturn(lifeCycle)
        lifeCycle.handleLifecycleEvent(Lifecycle.Event.ON_START)

    }

    /**
     * Verify when Initilize viewModel, the first action is getCurrentLocation
     */
    /*@Test
    fun `when viewModel is initilize then get currentLocation`() {
        runBlocking {
            viewModel.initialize()
            Mockito.verify(getCurrentLocationUseCase, Mockito.times(1)).execute()
        }
    }*/

    /**
     * Verify that "getCurrentWeatherByNameUseCase" is executed once when call getCityCurrentWeather()
     */
    @Test
    fun `should request the current weather when call getCityCurrentWeather()`() {
        runBlocking {
            val response = Response.Success(CurrentWeatherFactory.createCurrentWeatherTest())
            val request = GetCurrentWeatherByNameRequest(CITY_NAME)
            whenever(getCurrentWeatherByNameUseCase.execute(request)).thenReturn(response)

            viewModel.getCityCurrentWeather(CITY_NAME)
            Mockito.verify(getCurrentWeatherByNameUseCase, Mockito.times(1)).execute()
        }
    }

    /**
     * Verify Loading State is set when make request to get current weather
     */
    @Test
    fun `should loading state when make request`() {
        runBlocking {
            viewModel.getCityCurrentWeather(CITY_NAME)

            assertThat(viewModel.currentWeatherStateLiveData.value, instanceOf(LoadingCurrentWeatherState::class.java))
        }
    }

    /**
     * Verify when is success getting currentWeatherByName the liveData is changed with this new values
     */
    @Test
    fun `should show current weather when current weather info is received`() {
        runBlocking {
            val response = Response.Success(CurrentWeatherFactory.createCurrentWeatherTest())
            val request = GetCurrentWeatherByNameRequest(CITY_NAME)
            whenever(getCurrentWeatherByNameUseCase.execute(request)).thenReturn(response)

            viewModel.currentWeatherStateLiveData.observe(lifeCycleOwner, observer)

            viewModel.getCityCurrentWeather(CITY_NAME)

            verify(observer).onChanged(
                DefaultCurrentWeatherState(
                    response
                )
            )
        }

    }

    /**
     * Test that checks ErrorState is set when there is no Internet connection
     */
    @Test
    fun `should show error when no Internet connection is available`() {
        runBlocking {
            val response = Response.Error(exception = NetworkConnectionException())
            val request = GetCurrentWeatherByNameRequest(CITY_NAME)
            whenever(getCurrentWeatherByNameUseCase.execute(request)).thenReturn(response)

            viewModel.currentWeatherStateLiveData.observe(lifeCycleOwner, observer)

            viewModel.getCityCurrentWeather(CITY_NAME)

            verify(observer).onChanged(
                ErrorCurrentWeatherState(
                    response
                )
            )
        }
    }

    private fun prepareViewModel(){
        viewModel = WeatherViewModel(getCurrentWeatherByNameUseCase, getCurrentLocationUseCase, getWeatherListUseCase, coroutineContext)
    }
}