package es.jarroyo.books.ui.bookDetails.activity

import android.os.Bundle
import es.jarroyo.books.app.di.component.ApplicationComponent
import es.jarroyo.books.app.di.subcomponent.bookDetails.activity.BookDetailsActivityModule
import es.jarroyo.books.domain.model.books.Item
import es.jarroyo.books.ui.base.BaseBackActivity
import es.jarroyo.books.ui.bookDetails.fragment.BookDetailsFragment

class BookDetailsActivity : BaseBackActivity() {

    // INTENT DATA
    lateinit var mBook: Item

    override fun setupInjection(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(BookDetailsActivityModule(this)).injectTo(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getExtras()

        addFragmentToMainLayout(BookDetailsFragment.newInstance(mBook))
    }

    fun getExtras() {
        mBook = intent.extras.getSerializable(ARG_EXTRA_BOOK) as Item
    }

    companion object {
        val ARG_EXTRA_BOOK = "ARG_EXTRA_BOOK"
    }
}
