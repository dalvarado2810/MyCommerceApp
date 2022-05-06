package com.example.mycommerceapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import com.example.mycommerceapp.data.model.SearchModel
import com.example.mycommerceapp.data.repositories.SearchRepository
import com.example.mycommerceapp.util.CoroutineTestRule
import com.example.mycommerceapp.util.MainCoroutineRule
import com.example.mycommerceapp.util.getOrAwaitValue
import com.example.mycommerceapp.viewmodel.AppResource
import com.example.mycommerceapp.viewmodel.SearchViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class EcommerceViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get: Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var coroutineRule: CoroutineTestRule = CoroutineTestRule()

    private lateinit var viewModel: SearchViewModel

    @MockK
    private lateinit var repository: SearchRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        viewModel = SearchViewModel(repository)
    }

    @Test
    fun `when search is called, livedata value is set`() =
        mainCoroutineRule.runBlockingTest {
            viewModel.getAllSearchItems("iphone")
            val vmResult = viewModel.liveData.getOrAwaitValue (12)
            Assert.assertNotNull(vmResult)
        }
}
