package es.jarroyo.books.ui

import es.jarroyo.books.app.di.DaggerTestApplicationComponent
import es.jarroyo.books.app.di.TestApplicationModule

open class BooksApp : App() {

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    private fun initializeDagger() {
        graph = DaggerTestApplicationComponent.builder()
                .testApplicationModule(TestApplicationModule(this))
                .build()
    }
}