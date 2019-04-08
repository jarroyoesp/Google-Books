package es.jarroyo.books.ui.bookDetails.fragment


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.OnClick
import es.jarroyo.books.R
import es.jarroyo.books.app.di.component.ApplicationComponent
import es.jarroyo.books.domain.model.books.Item
import es.jarroyo.books.ui.base.BaseFragment
import es.jarroyo.books.ui.base.loadRoundedUrl
import es.jarroyo.books.utils.Utils
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
        if (mBook.volumeInfo?.imageLinks == null) {
            fragment_book_details_iv_project.visibility = View.GONE
        } else {
            var url = mBook.volumeInfo?.imageLinks?.thumbnail!!.replace(
                "http",
                "https"
            )


            fragment_book_details_iv_project.loadRoundedUrl(context!!,url)
        }

        // TITLE & DESCRIPTION
        fragment_book_details_tv_title.text = mBook.volumeInfo?.title
        fragment_book_details_tv_description.text = mBook.volumeInfo?.description

        // PRICE
        if (mBook.saleInfo?.listPrice != null) {
            fragment_book_details_tv_price.text =
                "${mBook.saleInfo?.listPrice?.amount}${mBook.saleInfo?.listPrice?.currencyCode}"
        }

        // PDF
        if (mBook.accessInfo?.webReaderLink != null && mBook.accessInfo?.webReaderLink!!.isNotEmpty()) {
            fragment_book_details_button_read.visibility = View.VISIBLE
        } else {
            fragment_book_details_button_read.visibility = View.GONE
        }
    }

    /**
     * ON CLICK
     */
    @OnClick(R.id.fragment_book_details_button_read)
    fun onClickRead() {
        Utils.openUrl(context!!, mBook.accessInfo?.webReaderLink!!)
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
