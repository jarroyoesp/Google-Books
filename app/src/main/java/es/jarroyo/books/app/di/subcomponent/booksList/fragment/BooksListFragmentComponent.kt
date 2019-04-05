package es.jarroyo.books.app.di.subcomponent.booksList.fragment

import dagger.Subcomponent
import es.jarroyo.books.ui.booksList.fragment.BooksListFragment

@Subcomponent(modules = arrayOf(BooksListFragmentModule::class))
interface BooksListFragmentComponent {
    fun injectTo(fragment: BooksListFragment)
}