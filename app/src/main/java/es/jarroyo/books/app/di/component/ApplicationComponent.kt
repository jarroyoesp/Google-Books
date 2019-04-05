package es.jarroyo.books.app.di.component

import dagger.Component
import es.jarroyo.books.app.di.module.*
import es.jarroyo.books.app.di.subcomponent.bookDetails.activity.BookDetailsActivityComponent
import es.jarroyo.books.app.di.subcomponent.bookDetails.activity.BookDetailsActivityModule
import es.jarroyo.books.app.di.subcomponent.booksList.activity.BooksListActivityComponent
import es.jarroyo.books.app.di.subcomponent.booksList.activity.BooksListActivityModule
import es.jarroyo.books.app.di.subcomponent.booksList.fragment.BooksListFragmentComponent
import es.jarroyo.books.app.di.subcomponent.booksList.fragment.BooksListFragmentModule
import es.jarroyo.books.app.di.viewmodel.ViewModelFactoryModule
import es.jarroyo.books.app.di.viewmodel.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        ApplicationModule::class,
        UtilsModule::class,
        RepositoryModule::class,
        DataModule::class,
        DomainModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class
    )
)
interface ApplicationComponent {
    /**
     * UI - ACTIVITY
     */

    /**
     * BOOKS LIST
     */
    fun plus(module: BooksListActivityModule): BooksListActivityComponent
    fun plus(module: BooksListFragmentModule): BooksListFragmentComponent

    /**
     * BOOKS DETAILS
     */
    fun plus(module: BookDetailsActivityModule): BookDetailsActivityComponent

}