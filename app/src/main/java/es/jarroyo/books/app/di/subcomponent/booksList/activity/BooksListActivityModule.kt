package es.jarroyo.books.app.di.subcomponent.booksList.activity

import dagger.Module
import es.jarroyo.books.app.di.module.ActivityModule
import es.jarroyo.books.ui.booksList.activity.BooksListActivity

@Module
class BooksListActivityModule(activity: BooksListActivity) : ActivityModule(activity) {

}