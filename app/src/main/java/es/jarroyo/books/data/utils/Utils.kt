package es.jarroyo.books.data.utils

import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

object Utils {

    fun readJsonFromAssets(context: Context, fileName: String): String {
        val buf = StringBuilder()
        val json: InputStream
        try {
            json = context.getAssets().open(fileName)

            val bufferIn = BufferedReader(InputStreamReader(json, "UTF-8"))
            var str: String
            str = bufferIn.readLine()
            while (str != null) {
                buf.append(str)
                str = bufferIn.readLine()
            }

            bufferIn.close()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            return buf.toString()
        }
    }
}