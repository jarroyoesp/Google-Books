package es.jarroyo.books.ui.booksList.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import es.jarroyo.books.R
import es.jarroyo.books.app.di.component.ApplicationComponent
import es.jarroyo.books.app.di.subcomponent.booksList.activity.BooksListActivityModule
import es.jarroyo.books.ui.base.BaseActivity
import es.jarroyo.books.ui.bookDetails.activity.BookDetailsActivity
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
        var bundle = Bundle()
        bundle.putSerializable(BookDetailsActivity.ARG_EXTRA_BOOK, itemBook.book)


        val p1 = Pair<View, String>(itemBook.itemView.findViewById(R.id.item_rv_book_iv_avatar), "transitionIVDetails")
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, p1)

        val intent = Intent(this, BookDetailsActivity::class.java)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent, options?.toBundle())
    }
}
