package com.example.kaizenspeaking.ui.auth.view_model

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.kaizenspeaking.ui.auth.data.LoginBody
import com.example.kaizenspeaking.ui.auth.data.User
import com.example.kaizenspeaking.ui.auth.repository.AuthRepository
import com.example.kaizenspeaking.ui.auth.utils.RequesStatus
import com.example.kaizenspeaking.ui.auth.view_model.SignInActivityViewModel
import com.example.kaizenspeaking.utils.UserSession
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class SignInActivityViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SignInActivityViewModel

    @Mock
    private lateinit var authRepository: AuthRepository

    @Mock
    private lateinit var application: Application

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var sharedPreferences: SharedPreferences

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = SignInActivityViewModel(authRepository, application)

        // Mock SharedPreferences behavior
        `when`(context.getSharedPreferences(anyString(), anyInt())).thenReturn(sharedPreferences)
        `when`(sharedPreferences.edit()).thenReturn(mock(SharedPreferences.Editor::class.java))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loginUser mengeluarkan kesuksesan ketika login berhasil`() = runTest {
        // Arrange
        val loginBody = LoginBody("test@example.com", "password123")
        val user = User(
            id = "123",
            email = "test@example.com",
            full_name = "Test Full Name",
            name = "Test User",
            access_token = "token123",
            userId = "12345"
        )
        val successResponse = RequesStatus.Success(user)

        // Create the Flow response
        val responseFlow: Flow<RequesStatus<User>> = flowOf(successResponse)

        // Stub the repository's loginUser method
        whenever(authRepository.loginUser(loginBody)).thenReturn(responseFlow)

        // Observe the User LiveData
        val userObserver = mock(Observer::class.java) as Observer<User>
        viewModel.getUser().observeForever(userObserver)

        // Act
        viewModel.loginUser(loginBody, context)

        // Assert
        verify(userObserver).onChanged(user)
    }

    @Test
    fun `loginUser menampilkan loading ke true saat login`() = runTest {
        // Arrange
        val loginBody = LoginBody("test@example.com", "password123")
        val waitingResponse = RequesStatus.Waiting
        val successResponse = RequesStatus.Success(
            User("123", "test@example.com", "Test User", "testuser", "token", "1234")
        )

        // Create the Flow response
        val responseFlow: Flow<RequesStatus<User>> = flowOf(waitingResponse, successResponse)

        // Stub the repository's loginUser method
        whenever(authRepository.loginUser(loginBody)).thenReturn(responseFlow)

        // Observe the IsLoading LiveData
        val isLoadingObserver = mock(Observer::class.java) as Observer<Boolean>
        viewModel.getIsLoading().observeForever(isLoadingObserver)

        // Act
        viewModel.loginUser(loginBody, context)

        // Assert
        val inOrder = inOrder(isLoadingObserver)
        inOrder.verify(isLoadingObserver).onChanged(true)
        inOrder.verify(isLoadingObserver).onChanged(false)
    }

}
