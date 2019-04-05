package es.jarroyo.books.ui.bookDetails.fragment


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.jarroyo.books.R
import es.jarroyo.books.app.di.component.ApplicationComponent
import es.jarroyo.books.domain.model.books.Item
import es.jarroyo.books.ui.base.BaseFragment
import es.jarroyo.books.ui.base.loadRoundedUrl
import kotlinx.android.synthetic.main.fragment_book_details.*

class BookDetailsFragment : BaseFragment() {

    override var layoutId = R.layout.fragment_book_details

    // INTENT DATA
    lateinit var mBook: Item

    override fun setupInjection(applicationComponent: ApplicationComponent) {

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        getExtras()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflateView(inflater, container)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configView()
    }

    private fun getExtras() {
        mBook = arguments?.getSerializable(ARG_EXTRA_BOOK) as Item
    }

    private fun configView() {
        // Image
        if (mBook.volumeInfo.imageLinks == null) {
            fragment_book_details_iv_project.visibility = View.GONE
        } else {
            var url = mBook.volumeInfo.imageLinks?.thumbnail!!.replace(
                "http",
                "https"
            )


            fragment_book_details_iv_project.loadRoundedUrl(context!!,url)
        }
        fragment_book_details_tv_title.text = mBook.volumeInfo.title
        fragment_book_details_tv_description.text = mBook.volumeInfo.description
    }

    companion object {
        val ARG_EXTRA_BOOK = "ARG_EXTRA_BOOK"

        fun newInstance(book: Item) = BookDetailsFragment().apply {
            arguments = Bundle().apply {
                putSerializable(ARG_EXTRA_BOOK, book)
            }
        }
    }
}
