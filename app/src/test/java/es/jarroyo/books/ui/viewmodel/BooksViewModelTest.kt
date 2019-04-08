package es.jarroyo.books.ui.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.whenever
import es.jarroyo.books.domain.model.Response
import es.jarroyo.books.domain.model.books.BooksListResponse
import es.jarroyo.books.domain.usecase.books.getBooksList.GetBooksListRequest
import es.jarroyo.books.domain.usecase.books.getBooksList.GetBooksListUseCase
import es.jarroyo.books.ui.viewmodel.books.BooksViewModel
import es.jarroyo.books.ui.viewmodel.books.ErrorGetBooksListState
import es.jarroyo.books.ui.viewmodel.books.GetBooksListState
import es.jarroyo.books.ui.viewmodel.books.SuccessGetBooksListState
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
import org.mockito.MockitoAnnotations
import kotlin.coroutines.CoroutineContext



class BooksViewModelTest {

    @get: Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit  var viewModel : BooksViewModel

    var coroutineContext: CoroutineContext = Dispatchers.Unconfined

    @Mock
    lateinit var getBooksListUseCase: GetBooksListUseCase

    @Mock
    lateinit var observer: Observer<GetBooksListState>

    @Mock
    lateinit var lifeCycleOwner: LifecycleOwner

    lateinit var lifeCycle: LifecycleRegistry

    @Mock
    lateinit var mContext: Context


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        prepareViewModel()

        lifeCycle = LifecycleRegistry(lifeCycleOwner)
        `when` (lifeCycleOwner.lifecycle).thenReturn(lifeCycle)
        lifeCycle.handleLifecycleEvent(Lifecycle.Event.ON_START)

    }

    /**
     * Verify that "getBookListUseCase" is executed once when call getCityCurrentWeather()
     */
    @Test
    fun `should request bookList when call getBooksList()`() {
        runBlocking {
            val response = Response.Success(createResponse())
            val request = GetBooksListRequest("query")
            whenever(getBooksListUseCase.execute(request)).thenReturn(response)

            viewModel.getBooksList("query")
            Mockito.verify(getBooksListUseCase, Mockito.times(1)).execute()
        }
    }

    /**
     * Verify Success State is set when make request to get data
     */
    @Test
    fun `should success state when finish request`() {
        runBlocking {
            val response = Response.Success(createResponse())
            val request = GetBooksListRequest("query")
            whenever(getBooksListUseCase.execute(request)).thenReturn(response)

            viewModel.getBooksList("Query")

            assertThat(viewModel.getBooksListStateLiveData.value, instanceOf(SuccessGetBooksListState::class.java))
        }
    }


    /**
     * Verify ERROR State is set when make request to get data and NO internet
     */
    @Test
    fun `should error state when no internet`() {
        runBlocking {
            val response = Response.Error(IllegalAccessException())
            val request = GetBooksListRequest("query")
            whenever(getBooksListUseCase.execute(request)).thenReturn(response)

            viewModel.getBooksList("Query")

            assertThat(viewModel.getBooksListStateLiveData.value, instanceOf(ErrorGetBooksListState::class.java))
        }
    }

    private fun prepareViewModel(){
        viewModel = BooksViewModel(getBooksListUseCase, coroutineContext)
    }


    fun createResponse(): BooksListResponse {
        var gson = Gson()
        val json = response
        return gson?.fromJson(json, BooksListResponse::class.java)
    }

    val response = "{\n" +
            " \"kind\": \"books#volumes\",\n" +
            " \"totalItems\": 1224,\n" +
            " \"items\": [\n" +
            "  {\n" +
            "   \"kind\": \"books#volume\",\n" +
            "   \"id\": \"uUOBPgXQtvUC\",\n" +
            "   \"etag\": \"UNVYHgkACDc\",\n" +
            "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/uUOBPgXQtvUC\",\n" +
            "   \"volumeInfo\": {\n" +
            "    \"title\": \"Harry Potter y la Orden del Fénix\",\n" +
            "    \"authors\": [\n" +
            "     \"J.K. Rowling\"\n" +
            "    ],\n" +
            "    \"publisher\": \"Pottermore Publishing\",\n" +
            "    \"publishedDate\": \"2015-12-08\",\n" +
            "    \"description\": \"Las vacaciones de verano aún no han acabado y Harry se encuentra más inquieto que nunca. Apenas ha tenido noticias de Ron y Hermione, y presiente que algo extraño está sucediendo en Hogwarts. No bien empieza el nuevo curso, sus temores se vuelven realidad: el Ministerio de Magia ha iniciado una campaña de desprestigio contra él y Dumbledore, para lo cual ha asignado a la horrible profesora Dolores Umbridge la tarea de vigilar sus movimientos. Y por si fuera poco, Harry sospecha que Voldemort es capaz de adivinar sus pensamientos con el fin de apoderarse de un objeto secreto que le permitiría recuperar su poder destructivo.\",\n" +
            "    \"industryIdentifiers\": [\n" +
            "     {\n" +
            "      \"type\": \"ISBN_13\",\n" +
            "      \"identifier\": \"9781781101353\"\n" +
            "     },\n" +
            "     {\n" +
            "      \"type\": \"ISBN_10\",\n" +
            "      \"identifier\": \"1781101353\"\n" +
            "     }\n" +
            "    ],\n" +
            "    \"readingModes\": {\n" +
            "     \"text\": true,\n" +
            "     \"image\": true\n" +
            "    },\n" +
            "    \"pageCount\": 928,\n" +
            "    \"printType\": \"BOOK\",\n" +
            "    \"categories\": [\n" +
            "     \"Juvenile Fiction\"\n" +
            "    ],\n" +
            "    \"averageRating\": 4.0,\n" +
            "    \"ratingsCount\": 32,\n" +
            "    \"maturityRating\": \"NOT_MATURE\",\n" +
            "    \"allowAnonLogging\": true,\n" +
            "    \"contentVersion\": \"1.5.5.0.preview.3\",\n" +
            "    \"panelizationSummary\": {\n" +
            "     \"containsEpubBubbles\": false,\n" +
            "     \"containsImageBubbles\": false\n" +
            "    },\n" +
            "    \"imageLinks\": {\n" +
            "     \"smallThumbnail\": \"http://books.google.com/books/content?id=uUOBPgXQtvUC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "     \"thumbnail\": \"http://books.google.com/books/content?id=uUOBPgXQtvUC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "    },\n" +
            "    \"language\": \"es\",\n" +
            "    \"previewLink\": \"http://books.google.es/books?id=uUOBPgXQtvUC&printsec=frontcover&dq=harrypotter&hl=&cd=1&source=gbs_api\",\n" +
            "    \"infoLink\": \"https://play.google.com/store/books/details?id=uUOBPgXQtvUC&source=gbs_api\",\n" +
            "    \"canonicalVolumeLink\": \"https://market.android.com/details?id=book-uUOBPgXQtvUC\"\n" +
            "   },\n" +
            "   \"saleInfo\": {\n" +
            "    \"country\": \"ES\",\n" +
            "    \"saleability\": \"FOR_SALE\",\n" +
            "    \"isEbook\": true,\n" +
            "    \"listPrice\": {\n" +
            "     \"amount\": 8.99,\n" +
            "     \"currencyCode\": \"EUR\"\n" +
            "    },\n" +
            "    \"retailPrice\": {\n" +
            "     \"amount\": 8.99,\n" +
            "     \"currencyCode\": \"EUR\"\n" +
            "    },\n" +
            "    \"buyLink\": \"https://play.google.com/store/books/details?id=uUOBPgXQtvUC&rdid=book-uUOBPgXQtvUC&rdot=1&source=gbs_api\",\n" +
            "    \"offers\": [\n" +
            "     {\n" +
            "      \"finskyOfferType\": 1,\n" +
            "      \"listPrice\": {\n" +
            "       \"amountInMicros\": 8990000.0,\n" +
            "       \"currencyCode\": \"EUR\"\n" +
            "      },\n" +
            "      \"retailPrice\": {\n" +
            "       \"amountInMicros\": 8990000.0,\n" +
            "       \"currencyCode\": \"EUR\"\n" +
            "      },\n" +
            "      \"giftable\": true\n" +
            "     }\n" +
            "    ]\n" +
            "   },\n" +
            "   \"accessInfo\": {\n" +
            "    \"country\": \"ES\",\n" +
            "    \"viewability\": \"PARTIAL\",\n" +
            "    \"embeddable\": true,\n" +
            "    \"publicDomain\": false,\n" +
            "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
            "    \"epub\": {\n" +
            "     \"isAvailable\": true,\n" +
            "     \"acsTokenLink\": \"http://books.google.es/books/download/Harry_Potter_y_la_Orden_del_F%C3%A9nix-sample-epub.acsm?id=uUOBPgXQtvUC&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
            "    },\n" +
            "    \"pdf\": {\n" +
            "     \"isAvailable\": true,\n" +
            "     \"acsTokenLink\": \"http://books.google.es/books/download/Harry_Potter_y_la_Orden_del_F%C3%A9nix-sample-pdf.acsm?id=uUOBPgXQtvUC&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
            "    },\n" +
            "    \"webReaderLink\": \"http://play.google.com/books/reader?id=uUOBPgXQtvUC&hl=&printsec=frontcover&source=gbs_api\",\n" +
            "    \"accessViewStatus\": \"SAMPLE\",\n" +
            "    \"quoteSharingAllowed\": false\n" +
            "   },\n" +
            "   \"searchInfo\": {\n" +
            "    \"textSnippet\": \"Las vacaciones de verano aún no han acabado y Harry se encuentra más inquieto que nunca.\"\n" +
            "   }\n" +
            "  } ]\n" +
            "}"

}