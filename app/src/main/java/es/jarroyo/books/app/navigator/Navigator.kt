package es.jarroyo.books.app.navigator

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import es.jarroyo.books.domain.model.books.Item
import es.jarroyo.books.ui.base.BaseActivity
import es.jarroyo.books.ui.bookDetails.activity.BookDetailsActivity

class Navigator {

    var currentActivity: BaseActivity? = null

    private fun toDefaultActivity(activity: Class<*>, bundle: Bundle? = null, activityOptionsCompat: ActivityOptionsCompat? = null) {
        val intent = Intent(currentActivity, activity)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        currentActivity?.startActivity(intent, activityOptionsCompat?.toBundle())
    }

    private fun toDefaultActivityForResult(requestCode: Int, activity: Class<*>, bundle: Bundle? = null) {
        val intent = Intent(currentActivity, activity)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        currentActivity?.startActivityForResult(intent, requestCode)
    }

    private fun toDefaultActivityCleaningStack(activity: Class<*>, bundle: Bundle? = null) {
        val intent = Intent(currentActivity, activity)

        if (bundle != null) {
            intent.putExtras(bundle)
        }

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        currentActivity?.startActivity(intent)
    }

    /***********************************************************************************************
     *  ACTIVITIES
     **********************************************************************************************/

    fun toBookDetailsActivity(book: Item, viewIVTransition: View) {
        var bundle = Bundle()
        bundle.putSerializable(BookDetailsActivity.ARG_EXTRA_BOOK, book)


        val p1 = Pair<View, String>(viewIVTransition, "transitionIVDetails")
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(currentActivity!!, p1)

        toDefaultActivity(BookDetailsActivity::class.java, bundle, options)

    }
}
