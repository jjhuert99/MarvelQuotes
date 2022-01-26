package com.example.marvelquotes

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.marvelquotes.network.Quote
import com.example.marvelquotes.network.QuoteNetworkImpl
import com.example.marvelquotes.network.ServiceResult
import com.example.marvelquotes.ui.home.HomeViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModelTest: HomeViewModel
    private val testApp = mockk<Application>(relaxed = true)
    private val testRepo = mockk<QuoteNetworkImpl>(relaxed = true)
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp(){
        Dispatchers.setMain(TestCoroutineDispatcher())
        viewModelTest = HomeViewModel(
            app = testApp,
            QuoteRepo = testRepo,
            dispatcher = testDispatcher
        )
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `getQuote should return a quote with the movie its from and the character who says it`() = runBlockingTest {
        coEvery{ testRepo.getQuote() } returns createSuccessfulQuoteCall()

        viewModelTest.getQuotePost()

        assertEquals(
            false, viewModelTest.post.value?.Title.isNullOrEmpty()
        )

        assertEquals(
            createSuccessfulQuoteCall().data.Quote, viewModelTest.post.value?.Quote
        )
        
    }

    private fun createSuccessfulQuoteCall(): ServiceResult.Succes<Quote> {
        return ServiceResult.Succes(
            mockk<Quote>(relaxed = true)
        )
    }

}
