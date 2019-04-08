package es.jarroyo.books.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.TypedValue
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.FragmentActivity

object Utils {
    /**
     * HIDE KEYBOARD
     */
    fun hideKeyboard(activity: FragmentActivity){
        val view = activity.getCurrentFocus()
        if (view != null) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view!!.getWindowToken(), 0)
        }
    }

    /**
     * SHOW KEYBOARD
     */
    fun showKeyboard(context: Context, editText: EditText){
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        editText.requestFocus()
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }


    /**
     * CONVERT DP TO PX
     */
    fun convertDpToPixel(context: Context, dp: Float): Float {
        val metrics = context.getResources().getDisplayMetrics()
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics)
    }

    /**
     * OPEN URL
     */
    fun openUrl(context: Context, url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(browserIntent)
    }

}