package es.jarroyo.books.ui.base

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import es.jarroyo.books.R
import kotlinx.android.synthetic.main.activity_base_back.*

abstract class BaseBackActivity : BaseActivity() {
    override var layoutId = R.layout.activity_base_back

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configView()
    }

    fun configView(){
        prepareToolbar()
    }


    private fun prepareToolbar() {
        setSupportActionBar(activity_base_back_toolbar)
        showBackButton()
    }

    private fun showBackButton() {
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
            supportActionBar!!.setTitle("")
            val upArrow = ContextCompat.getDrawable(this, R.drawable.ic_back_button)
            supportActionBar!!.setHomeAsUpIndicator(upArrow)
        }
    }

    fun hideToolbar() {
        if (supportActionBar != null) {
            activity_base_back_toolbar.visibility = View.GONE
            supportActionBar!!.setDisplayHomeAsUpEnabled(false)
            supportActionBar!!.setDisplayShowHomeEnabled(false)
        }
    }

    fun setTitleToolbar(title: String) {
        supportActionBar?.title = title

    }

    /**
     * ADD FRAGMENT TO MAIN LAYOUT
     */
    fun addFragmentToMainLayout(fragment: Fragment){
        val ft = supportFragmentManager?.beginTransaction()
        ft?.replace(R.id.activity_base_back_layout_fragment_container, fragment)?.commit()
    }
}
