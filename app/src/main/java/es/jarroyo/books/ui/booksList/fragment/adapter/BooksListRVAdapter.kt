package es.jarroyo.books.ui.booksList.fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.jarroyo.books.R
import es.jarroyo.books.domain.model.books.Item
import es.jarroyo.books.ui.base.loadRoundedUrl
import kotlinx.android.synthetic.main.item_rv_book.view.*

class BooksListRVAdapter(
    private var mBooksList: List<Item>? = listOf<Item>(),
    private val listenerBookClicked: (ItemBook) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        if (mBooksList != null) {
            return mBooksList?.size!!
        }
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_rv_book, parent, false)
                return WeatherViewHolder(itemView)


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

                val mBook = mBooksList?.get(position)
                val weatherHolder = holder as WeatherViewHolder
                weatherHolder.bind(mBook!!, position, listenerBookClicked)

    }

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(book: Item, position: Int, listener: (ItemBook) -> Unit) = with(itemView) {

            // TITLE
            item_rv_book_tv_title.text = book.volumeInfo?.title

            // IMAGE
            if (book.volumeInfo?.imageLinks != null) {
                item_rv_book_iv_avatar.visibility = View.VISIBLE
                var url = book.volumeInfo?.imageLinks.thumbnail.replace("http", "https")
                item_rv_book_iv_avatar.loadRoundedUrl(context!!, url)
            } else {
                item_rv_book_iv_avatar.visibility = View.GONE
            }

            // AUTHOR
            if (book.volumeInfo?.authors.isNullOrEmpty()) {
                item_rv_book_tv_author.visibility = View.GONE
            } else {
                item_rv_book_tv_author.visibility = View.VISIBLE
                item_rv_book_tv_author.text = book.volumeInfo?.getAuthorsString()
            }
            // DESCRIPTION
            if (book.volumeInfo?.description.isNullOrEmpty()) {
                item_rv_book_tv_description.visibility = View.GONE
            } else {
                item_rv_book_tv_description.visibility = View.VISIBLE
                item_rv_book_tv_description.text = book.volumeInfo?.description
            }

            // PRICE
            if (book.saleInfo?.listPrice != null) {
                item_rv_book_tv_price.visibility = View.VISIBLE
                item_rv_book_tv_price.text = "${book.saleInfo.listPrice.amount} ${book.saleInfo.listPrice.currencyCode}"
            } else {
                item_rv_book_tv_price.visibility = View.GONE
            }



            setOnClickListener {
                listener(ItemBook(position, book, this))
            }
        }
    }

    fun setBookList(bookList: List<Item>?) {
        mBooksList = bookList
    }
}

data class ItemBook(val position: Int, val book: Item, val itemView: View)