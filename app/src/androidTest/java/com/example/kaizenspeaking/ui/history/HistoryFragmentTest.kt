import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.kaizenspeaking.MainActivity
import com.example.kaizenspeaking.R
import com.example.kaizenspeaking.utils.EspressoIdlingResource
import org.hamcrest.CoreMatchers.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HistoryFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun testNavigateToHistoryFragmentAndLogin() {
        // Klik pada Bottom Navigation untuk membuka HistoryFragment
        onView(withId(R.id.navigation_history)) // Ganti ID sesuai dengan ID menu di Bottom Navigation Bar
            .perform(click())
        println("Klik pada Bottom Navigation untuk membuka HistoryFragment")

        // Verifikasi bahwa HistoryFragment terbuka
        onView(withId(R.id.fragment_history_root)) // Ganti ID dengan ID root layout di HistoryFragment
            .check(matches(isDisplayed()))
        println("Verifikasi bahwa HistoryFragment terbuka")

        // Ketika dialog login muncul, tekan tombol Sign In
        onView(withText("MASUK KE AKUN")) // Ganti teks sesuai dengan teks di tombol dialog
            .perform(click())

        // Verifikasi bahwa berpindah ke SignInActivity
        onView(withId(R.id.activity_sign_in_root)) // Ganti ID dengan ID root layout di SignInActivity
            .check(matches(isDisplayed()))

        // Masukkan email
        onView(withId(R.id.etEmail)) // Ganti ID dengan ID input email di SignInActivity
            .perform(typeText("anam@mail.id"))
        closeSoftKeyboard()

        // Masukkan password
        onView(withId(R.id.etPassword)) // Ganti ID dengan ID input password di SignInActivity
            .perform(typeText("Anam1234"))
        closeSoftKeyboard()

        // Tekan tombol masuk
        onView(withId(R.id.btnMasuk)) // Ganti ID dengan ID tombol masuk di SignInActivity
            .perform(click())

        // Pastikan kembali ke HistoryFragment setelah login
        onView(withId(R.id.fragment_history_root)) // Ganti ID sesuai ID root layout di HistoryFragment
            .check(matches(isDisplayed()))
    }
}
