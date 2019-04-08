package es.jarroyo.books.domain.model.books

import java.io.Serializable

data class BooksListResponse(
    val items: List<Item> = mutableListOf(),
    val kind: String = "",
    val totalItems: Int = 0
): Serializable

data class Item(
    val accessInfo: AccessInfo? = null,
    val etag: String = "",
    val id: String = "",
    val kind: String = "",
    val saleInfo: SaleInfo? = null,
    val searchInfo: SearchInfo? = null,
    val selfLink: String = "",
    val volumeInfo: VolumeInfo? = null
): Serializable

data class SaleInfo(
    val country: String = "",
    val isEbook: Boolean = false,
    val saleability: String = "",
    val listPrice: Price? = null,
    val retailPrice: Price? = null
): Serializable

data class Price(val amount: String = "",
                 val currencyCode: String = ""): Serializable

data class SearchInfo(
    val textSnippet: String = ""
): Serializable

data class VolumeInfo(
    val allowAnonLogging: Boolean = false,
    val authors: List<String>? = mutableListOf(),
    val averageRating: Double = 0.0,
    val canonicalVolumeLink: String = "",
    val categories: List<String> = mutableListOf(),
    val contentVersion: String = "",
    val description: String = "",
    val imageLinks: ImageLinks? = null,
    val industryIdentifiers: List<IndustryIdentifier>? = null,
    val infoLink: String = "",
    val language: String = "",
    val maturityRating: String = "",
    val pageCount: Int = 0,
    val panelizationSummary: PanelizationSummary? = null,
    val previewLink: String = "",
    val printType: String = "",
    val publishedDate: String = "",
    val publisher: String = "",
    val ratingsCount: Int = 0,
    val readingModes: ReadingModes? = null,
    val subtitle: String = "",
    val title: String = ""
): Serializable {
    fun getAuthorsString(): String {
        var authorsString = ""
        if (authors != null) {
            for (author in authors) {
                authorsString = authorsString + author
            }
        }
        return authorsString
    }
}

data class ReadingModes(
    val image: Boolean = false,
    val text: Boolean = false
): Serializable

data class IndustryIdentifier(
    val identifier: String = "",
    val type: String = ""
): Serializable

data class ImageLinks(
    val smallThumbnail: String = "",
    val thumbnail: String = ""
): Serializable

data class PanelizationSummary(
    val containsEpubBubbles: Boolean = false,
    val containsImageBubbles: Boolean = false
): Serializable

data class AccessInfo(
    val accessViewStatus: String = "",
    val country: String = "",
    val embeddable: Boolean = false,
    val epub: Epub? = null,
    val pdf: Pdf? = null,
    val publicDomain: Boolean = false,
    val quoteSharingAllowed: Boolean = false,
    val textToSpeechPermission: String = "",
    val viewability: String = "",
    val webReaderLink: String = ""
): Serializable

data class Pdf(
    val acsTokenLink: String = "",
    val isAvailable: Boolean = false
): Serializable

data class Epub(
    val acsTokenLink: String = "",
    val isAvailable: Boolean = false
): Serializable