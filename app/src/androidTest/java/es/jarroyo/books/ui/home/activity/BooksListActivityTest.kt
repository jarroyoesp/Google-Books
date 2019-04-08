package es.jarroyo.books.ui.home.activity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.runner.AndroidJUnit4
import es.jarroyo.books.R
import es.jarroyo.books.app.baseTest.BaseActivityRule
import es.jarroyo.books.ui.booksList.activity.BooksListActivity
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
    fun should_request_locations_and_show_location_on_start(){
        mActivityRule.launchActivity()

        onView(withId(R.id.fragment_books_list_searchview)).check(matches(isDisplayed()))

    }

    /*@Test
    fun GIVEN_a_location_saved_WHEN_click_in_weather_THEN_show_forecast() {
        mActivityRule.launchActivity()

        // Check RV shows location test (Zaragoza) and Perform click
        onView(withRecyclerView(R.id.fragment_home_rv).atPosition(0))
            .check(matches(hasDescendant(withText(WeatherLocationFactory.locationTest)))).perform(click())

        // Check forecast detail is shown
        onView(withId(R.id.fragment_forecast_rv)).check(matches(isDisplayed()))

    }

   @Test
   fun GIVEN_app_init_WHEN_user_click_BottomNavigationView_THEN_show_fragments() {
       mActivityRule.launchActivity()

       onView(withId(R.id.navigation_account)).perform(click())

       onView(withId(R.id.navigation_home)).perform(click())

       onView(withRecyclerView(R.id.fragment_home_rv).atPosition(0))
           .check(matches(hasDescendant(withText(WeatherLocationFactory.locationTest))))

       onView(withId(R.id.navigation_account)).perform(click())

       pressBack()
   }

    fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId)
    }*/
}