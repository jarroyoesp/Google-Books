package es.jarroyo.books.app.di.subcomponent.bookDetails.activity

import dagger.Subcomponent
import es.jarroyo.books.ui.bookDetails.activity.BookDetailsActivity

@Subcomponent(modules = arrayOf(BookDetailsActivityModule::class))
interface BookDetailsActivityComponent {
    fun injectTo(activity: BookDetailsActivity)
}