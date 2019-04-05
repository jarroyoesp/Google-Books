package es.jarroyo.books.app.di.subcomponent.booksList.activity

import dagger.Subcomponent
import es.jarroyo.books.ui.booksList.activity.BooksListActivity

@Subcomponent(modules = arrayOf(BooksListActivityModule::class))
interface BooksListActivityComponent {
    fun injectTo(activity: BooksListActivity)
}