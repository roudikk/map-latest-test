@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.example.maplatesttest

import TestViewModel
import app.cash.turbine.test
import org.junit.Test

class TestViewModelTest {

    @Test
    fun `Test ViewModel`() = runCancellingTest {
        val viewModel = TestViewModel(this)

        viewModel.stateFlow.test {
            assert(awaitItem() == "Me!")
            viewModel.onValue("There")
            assert(awaitItem() == "Hello: There")
        }
    }
}