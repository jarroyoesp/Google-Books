package es.jarroyo.books.data.factory

import android.content.Context
import com.google.gson.Gson
import es.jarroyo.books.data.utils.Utils
import es.jarroyo.books.domain.model.books.BooksListResponse

object BooksListFactory {

    fun createBooksListResponse1(context: Context): BooksListResponse {
        var gson = Gson()
        val json = Utils.readJsonFromAssets(context, "response1.json")
        return gson?.fromJson(json, BooksListResponse::class.java)
    }
}