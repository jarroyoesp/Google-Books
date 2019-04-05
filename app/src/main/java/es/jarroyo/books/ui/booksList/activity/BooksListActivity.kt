package es.jarroyo.books.ui.booksList.activity

import android.os.Bundle
import es.jarroyo.books.R
import es.jarroyo.books.app.di.component.ApplicationComponent
import es.jarroyo.books.app.di.subcomponent.booksList.activity.BooksListActivityModule
import es.jarroyo.books.ui.base.BaseActivity

class BooksListActivity : BaseActivity() {
    override var layoutId = R.layout.activity_books_list

    override fun setupInjection(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(BooksListActivityModule(this)).injectTo(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
