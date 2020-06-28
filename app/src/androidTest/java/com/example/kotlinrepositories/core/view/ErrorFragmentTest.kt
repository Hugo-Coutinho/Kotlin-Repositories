package com.example.kotlinrepositories.core.view

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.kotlinrepositories.R
import org.junit.Before

@RunWith(AndroidJUnit4ClassRunner::class)
class ErrorFragmentTest {

    @Before
    fun setup() {
        launchFragmentInContainer { ErrorFragment("Something Wrong!!") }
    }

    @Test
    fun onCreateView_ShouldAssertErrorMessage() {
        onView(withId(R.id.tv_error_message)).check(matches(withText("Something Wrong!!")))
    }

    @Test
    fun onCreateView_ShouldAssertErrorAnimationIsDisplayed() {
        onView(withId(R.id.error_animation_view)).check(matches(isDisplayed()))
    }
}