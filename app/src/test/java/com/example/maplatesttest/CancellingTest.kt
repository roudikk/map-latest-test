package com.example.maplatesttest

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@OptIn(ExperimentalCoroutinesApi::class)
fun runCancellingTest(
    context: CoroutineContext = EmptyCoroutineContext,
    testBody: suspend TestScope.() -> Unit
) {
    try {
        runTest(context) {
            testBody(this)
            cancel(cause = TestJobCancellationException())
        }
    } catch (exception: TestJobCancellationException) {
        // Ignore our own cancellation exception.
    }
}

class TestJobCancellationException : CancellationException()