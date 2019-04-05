package es.jarroyo.books.app.di.subcomponent.bookDetails.activity

import dagger.Module
import es.jarroyo.books.app.di.module.ActivityModule
import es.jarroyo.books.ui.bookDetails.activity.BookDetailsActivity

@Module
class BookDetailsActivityModule(activity: BookDetailsActivity) : ActivityModule(activity) {

}