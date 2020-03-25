package com.assignment.coolwink.presentation


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.assignment.coolwink.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val textView = onView(
            allOf(
                withText("sindresorhus"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.rv_parentView),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("")))

        val textView2 = onView(
            allOf(
                withText("awesome"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.rv_parentView),
                        1
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("awesome")))

        val imageView = onView(
            allOf(
                withContentDescription("More options"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.action_bar),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))

        val textView3 = onView(
            allOf(
                withId(R.id.action_bar_title), withText("Tranding"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.action_bar),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("Tranding")))

        val textView4 = onView(
            allOf(
                withId(R.id.action_bar_title), withText("Tranding"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.action_bar),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("Tranding")))

        val textView5 = onView(
            allOf(
                withId(R.id.action_bar_title), withText("Tranding"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.action_bar),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView5.check(matches(withText("Tranding")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
