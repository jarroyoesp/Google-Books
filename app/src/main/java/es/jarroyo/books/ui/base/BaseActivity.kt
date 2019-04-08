package es.jarroyo.books.ui.base

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import es.jarroyo.books.app.di.component.ApplicationComponent
import es.jarroyo.books.app.navigator.Navigator
import es.jarroyo.books.ui.App
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    abstract var layoutId: Int

    abstract fun setupInjection(applicationComponent: ApplicationComponent)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupInjection(App.graph)
        initView()
    }

    override fun onResume() {
        super.onResume()
        navigator.currentActivity = this
    }

    private fun initView() {
        setContentView(layoutInflater.inflate(layoutId, null))
        ButterKnife.bind(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.getItemId()) {
            android.R.id.home -> {
                super.onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    protected fun cleanFragmentBackStack(){
        val fm = getSupportFragmentManager()
        for (i in 0 until fm.getBackStackEntryCount()) {
            fm.popBackStack()
        }
    }

    override fun onPause() {
        navigator.currentActivity = null
        super.onPause()
    }

    /*override fun onStop() {
        super.onStop()
        navigator.currentActivity = null
    }

    override fun onDestroy() {
        super.onDestroy()
        navigator.currentActivity = null
    }*/
}