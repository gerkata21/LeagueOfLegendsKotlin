package com.example.leagueoflegendskotlin

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.leagueoflegendskotlin", appContext.packageName)
    }

    @get:Rule
    val activityRule = ActivityScenarioRule(LoginActivity::class.java)
    private val email = "test@gmail.com"
    private val pass = "Password123"

    @Test
    fun login_views(){
        onView(withId(R.id.logoBar))
            .check(matches(isDisplayed()))

        onView(withId(R.id.email_address_tv))
            .check(matches(withText("Email Address")))
            .check(matches(isDisplayed()))

        onView(withId(R.id.Login_password))
            .check(matches(withText("Password")))
            .check(matches(isDisplayed()))

        onView(withId(R.id.loginBtn))
            .check(matches(isDisplayed()))

        onView(withId(R.id.alreadyHaveAccount))
            .check(matches(withText("New here? REGISTER")))
            .check(matches(isDisplayed()))
    }

    @Test
    fun login_successful(){
        onView(withId(R.id.Login_Email_Et))
            .perform(typeText(email), closeSoftKeyboard())

        onView(withId(R.id.Login_Password_Et))
            .perform(typeText(pass), closeSoftKeyboard())

        onView(withId(R.id.loginBtn))
            .perform(click())
            .check(matches(isDisplayed()))
    }

    @Test
    fun login_btn_functionality(){
        Intent()
        onView(withId(R.id.loginBtn)).perform(click())
    }
}