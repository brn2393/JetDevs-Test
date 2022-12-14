package com.imaginato.homeworkmvvm.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.imaginato.homeworkmvvm.data.remote.login.LoginRepository
import com.imaginato.homeworkmvvm.ui.base.IApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.rules.TestRule
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest

@Suppress("OPT_IN_USAGE")
class LoginViewModelTest : AutoCloseKoinTest() {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val testDispatcher: TestDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: LoginViewModel
    private lateinit var application: IApp
    private lateinit var repository: LoginRepository

    @Before
    fun setUp() {
        application = IApp()
        repository = provideLoginRepo()
        startKoin {
            listOf(module {
                single { repository }
            })
        }
        Dispatchers.setMain(testDispatcher)
        viewModel = LoginViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun performLogin() = runTest {
        //GIVEN

        //WHEN
        viewModel.performLogin("test", "test")
        advanceUntilIdle()
        //THEN
        Assert.assertTrue(viewModel.progress.value == false)
        Assert.assertEquals("Login by Bhushan", viewModel.result.value)
    }
}