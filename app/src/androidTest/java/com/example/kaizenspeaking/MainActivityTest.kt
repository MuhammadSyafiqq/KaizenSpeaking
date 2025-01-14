package com.example.kaizenspeaking

import android.util.Log
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.action.ViewActions
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.kaizenspeaking.ui.auth.SignInActivity
import com.example.kaizenspeaking.utils.UserSession
import com.example.kaizenspeaking.utils.EspressoIdlingResource
import com.example.kaizenspeaking.utils.wrapEspressoIdlingResource
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        // Register the idling resource
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)

        // Set the login status as false (logged out)
        activityRule.scenario.onActivity { activity ->
            UserSession.setLoggedIn(activity, false)
        }
    }

    @After
    fun tearDown() {
        // Unregister the idling resource after test completion
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)

        Intents.release()
    }

    @Test
    fun testSplashScreenDisplayed() {
        // Check if splash screen is displayed
        onView(withId(R.id.activity_splash)).check(matches(isDisplayed()))

        // Use idling resource to wait for any asynchronous tasks (if any) in the splash screen
        wrapEspressoIdlingResource {
            Thread.sleep(1500) // Mimic loading time but Espresso will wait for async tasks
        }
    }

    @Test
    fun testNavigateToHomeScreen() {
        // Verify that main screen is displayed after splash
        onView(withId(R.id.nav_host_fragment_activity_main)).check(matches(isDisplayed()))
    }

    @Test
    fun testLoginDialogDisplayedWhenNotLoggedIn() {
        // Check if dialog is displayed when user is not logged in
        onView(withText(R.string.pesan_belum_login)).check(matches(isDisplayed()))
    }

    @Test
    fun testSignInButtonInDialog() {
        Intents.init()

        // Click on the "Sign In" button and verify navigation to SignInActivity
        onView(withId(R.id.btnSignIn)).perform(ViewActions.click())
        Intents.intended(hasComponent(SignInActivity::class.java.name))

        Intents.release()
    }

    @Test
    fun testCloseButtonInDialog() {
        // Click on the close button and verify the dialog is dismissed
        onView(withId(R.id.btnClose)).perform(ViewActions.click())
        onView(withText(R.string.pesan_belum_login)).check(matches(not(isDisplayed())))
    }
}
