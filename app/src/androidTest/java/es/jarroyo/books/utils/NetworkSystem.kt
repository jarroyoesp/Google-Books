package es.jarroyo.books.utils

import android.content.Context

open class NetworkSystem(private val appContext : Context): NetworkSystemAbstract() {

    companion object {
        var mIsNetworkAvailable = true

        fun setNetworkAvailable (isNetworkAvailable: Boolean){
            mIsNetworkAvailable = isNetworkAvailable
        }
    }


    override fun isNetworkAvailable(): Boolean {
        return mIsNetworkAvailable
    }
}