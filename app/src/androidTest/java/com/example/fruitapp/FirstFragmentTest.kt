package com.example.fruitapp

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.fruitapp.fruitlist.FruitAdapter
import org.junit.Before
import org.junit.Test

class FirstFragmentTest {

    private val navController = TestNavHostController(getApplicationContext())
    @Before
    fun setUp(){
        launchFragmentInHiltContainer<FirstFragment> (Bundle(), R.style.Theme_FruitApp){
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(requireView(),navController)
        }
    }

    @Test
    fun checkIfRecyclerViewVisible(){
        onView(withId(R.id.recyclerView2))
            .check(matches(isDisplayed()))

    }
    @Test
    fun recyclerViewItemClick_shouldShowDetailFragment(){
        onView(withId(R.id.recyclerView2))
            .check(matches(isDisplayed()))
            .perform(RecyclerViewActions.actionOnItemAtPosition<FruitAdapter.MyViewHolder>(0,click()))
    //assert
        //assert(navController.currentDestination?.id == R.id.SecondFragment)

    }
}