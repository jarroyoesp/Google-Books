package es.jarroyo.books.ui.booksList.fragment


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.jarroyo.books.R
import es.jarroyo.books.app.di.component.ApplicationComponent
import es.jarroyo.books.app.di.subcomponent.booksList.fragment.BooksListFragmentModule
import es.jarroyo.books.app.navigator.Navigator
import es.jarroyo.books.domain.model.Response
import es.jarroyo.books.domain.model.books.BooksListResponse
import es.jarroyo.books.ui.base.BaseFragment
import es.jarroyo.books.ui.base.snackBar
import es.jarroyo.books.ui.base.toast
import es.jarroyo.books.ui.booksList.fragment.adapter.BooksListRVAdapter
import es.jarroyo.books.ui.booksList.fragment.adapter.ItemBook
import es.jarroyo.books.ui.viewmodel.books.*
import kotlinx.android.synthetic.main.fragment_books_list.*
import javax.inject.Inject


class BooksListFragment : BaseFragment() {
    override var layoutId = R.layout.fragment_books_list

    // View model
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var mBooksViewModel: BooksViewModel

    // RV Adapter
    private var mLayoutManager: LinearLayoutManager? = null
    private var mRvAdapter: BooksListRVAdapter? = null

    @Inject
    lateinit var navigator: Navigator

    private var mCurrentQuery: String = ""

    private var mListener: OnFragmentListener? = null

    override fun setupInjection(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(BooksListFragmentModule(this)).injectTo(this)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mListener = context as OnFragmentListener
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
        prepareViewModel()

        configRecyclerView()
        configSearchView()
    }

    private fun prepareViewModel() {
        //Observer
        mBooksViewModel = ViewModelProviders.of(this, viewModelFactory).get(BooksViewModel::class.java)
        observeBooksViewModel()
    }

    private fun getData(query: String) {
        mBooksViewModel.getBooksList(query)
    }

    /****************************************************************************
     * OBSERVER
     ***************************************************************************/
    /** FORECAST OBSERVER **/
    private fun observeBooksViewModel() {
        mBooksViewModel.getBooksListStateLiveData.observe(this, mGetGetBooksListStateObserver)
    }

    private val mGetGetBooksListStateObserver = Observer<GetBooksListState> { state ->
        state?.let {
            when (state) {
                is SuccessGetBooksListState -> {
                    hideLoading()
                    hideError()
                    val success = it.response as Response.Success
                    showBooksList(success.data)
                }
                is LoadingGetBooksListState -> {
                    showLoading()
                    hideError()
                }
                is ErrorGetBooksListState -> {
                    hideLoading()
                    showError((it as ErrorGetBooksListState))
                }
            }
        }
    }


    private fun showLoading(){
        fragment_books_list_swipe_refresh_rv.isRefreshing = true
    }

    private fun hideLoading(){
        fragment_books_list_swipe_refresh_rv.isRefreshing = false
    }

    private fun showError(errorGetForecastState: ErrorGetBooksListState) {
        val error = errorGetForecastState.response as Response.Error
        (activity as AppCompatActivity).snackBar(error.exception.message.toString(), onClickListener = object : View.OnClickListener{
            override fun onClick(p0: View?) {
                getData(mCurrentQuery)
            }

        })
    }

    private fun hideError(){

    }

    private fun showBooksList(booksListResponse: BooksListResponse) {
        if (booksListResponse == null || booksListResponse.items.isNullOrEmpty()) {
            fragment_books_list_swipe_refresh_rv.visibility = View.GONE
            fragment_books_list_layout_empty.visibility = View.VISIBLE
            fragment_books_list_layout_tv_status.text = getString(R.string.books_search_not_found)
        } else {

            fragment_books_list_swipe_refresh_rv.visibility = View.VISIBLE
            fragment_books_list_layout_empty.visibility = View.GONE
            mRvAdapter?.setBookList(booksListResponse.items)
            mRvAdapter?.notifyDataSetChanged()
        }
    }

    /**
     * CONFIG RV VIEW
     */
    fun configRecyclerView() {
        fragment_books_list_swipe_refresh_rv.isEnabled = false

        mLayoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL, false
        )
        fragment_books_list_rv.layoutManager = mLayoutManager

        mRvAdapter = BooksListRVAdapter(
            listenerBookClicked = {
                mListener?.onClickBook(it)
                //navigator.toBookDetailsActivity(it.book, it.itemView.findViewById(R.id.item_rv_book_iv_avatar))
            }
        )

        fragment_books_list_rv.adapter = mRvAdapter
        /*fragment_books_list_swipe_refresh_rv.setOnRefreshListener {
            //getData()
        }*/
    }

    fun configSearchView() {
        fragment_books_list_searchview.requestFocus()
        fragment_books_list_searchview.isIconified = false
        fragment_books_list_searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                if (text.isNullOrEmpty()) {
                    toast(getString(R.string.books_search_error_empty_query))
                } else {
                    mCurrentQuery = text
                    getData(text)
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }

        })
    }

    interface OnFragmentListener{
        fun onClickBook(itemBook: ItemBook)
    }

}
