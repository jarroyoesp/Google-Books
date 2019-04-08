package es.jarroyo.books.ui.booksList.activity

import android.os.Bundle
import es.jarroyo.books.R
import es.jarroyo.books.app.di.component.ApplicationComponent
import es.jarroyo.books.app.di.subcomponent.booksList.activity.BooksListActivityModule
import es.jarroyo.books.ui.base.BaseActivity
import es.jarroyo.books.ui.booksList.fragment.BooksListFragment
import es.jarroyo.books.ui.booksList.fragment.adapter.ItemBook

class BooksListActivity : BaseActivity(), BooksListFragment.OnFragmentListener {

    override var layoutId = R.layout.activity_books_list

    override fun setupInjection(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(BooksListActivityModule(this)).injectTo(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onClickBook(itemBook: ItemBook) {
        navigator.toBookDetailsActivity(itemBook.book, itemBook.itemView.findViewById(R.id.item_rv_book_iv_avatar))
    }
}
