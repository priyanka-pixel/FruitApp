package com.example.fruitapp

import androidx.core.os.bundleOf
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Before
import org.junit.Test

class SecondFragmentTest {
    @Before
    fun setUp(){
        launchFragmentInHiltContainer<SecondFragment>(bundleOf("id" to 1), R.style.Theme_FruitApp) { }
    }
    @Test
    fun fruitNameText_shouldVisible(){
        onView(withId(R.id.textView))
            .check(matches(isDisplayed()))
            .check(matches(withText("banana")))
    }
}