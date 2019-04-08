package es.jarroyo.books.ui.home.activity

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.runner.AndroidJUnit4
import es.jarroyo.books.R
import es.jarroyo.books.app.baseTest.BaseActivityRule
import es.jarroyo.books.data.factory.TestBooksListFactory
import es.jarroyo.books.ui.booksList.activity.BooksListActivity
import es.jarroyo.books.ui.utils.RecyclerViewMatcher
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class BooksListActivityTest {

    @Rule
    @JvmField
    var mActivityRule = BaseActivityRule(BooksListActivity::class.java, true, false)


    @Before
    fun insertData() {

    }


    @Test
    fun GIVEN_app_started_WHEN_view_is_init_THEN_searchview_is_shown(){
        mActivityRule.launchActivity()

        onView(withId(R.id.fragment_books_list_searchview)).check(matches(isDisplayed()))

    }

    @Test
    fun GIVEN_a_query_WHEN_click_in_search_THEN_show_books_list() {
        mActivityRule.launchActivity()

        onView(withId(R.id.fragment_books_list_searchview)).check(matches(isDisplayed())).perform(typeSearchViewText("query"))

        onView(withId(R.id.fragment_books_list_searchview))
            .perform(pressImeActionButton())

        onView(withRecyclerView(R.id.fragment_books_list_rv).atPosition(0))
            .check(matches(hasDescendant(withText(TestBooksListFactory.TEST_TITLE_POS_1)))).perform(click())

        // Check forecast detail is shown
        onView(withId(R.id.fragment_book_details_appbar_layout)).check(matches(isDisplayed()))

    }

    fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId)
    }

    fun typeSearchViewText(text: String): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                //Ensure that only apply if it is a SearchView and if it is visible.
                return allOf(isDisplayed(), isAssignableFrom(SearchView::class.java))
            }

            override fun getDescription(): String {
                return "Change view text"
            }

            override fun perform(uiController: UiController, view: View) {
                (view as SearchView).setQuery(text, false)
            }
        }
    }
}