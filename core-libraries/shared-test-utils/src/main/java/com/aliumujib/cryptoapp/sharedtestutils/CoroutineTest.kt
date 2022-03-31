package com.aliumujib.cryptoapp.sharedtestutils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Rule

@ExperimentalCoroutinesApi
abstract class CoroutineTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testDispatcher + Job())

    @After
    fun cleanupCoroutines() {
        testDispatcher.cleanupTestCoroutines()
        testDispatcher.resumeDispatcher()
    }

    fun coroutineScopedTest(block: suspend TestCoroutineScope.() -> Unit) =
        testCoroutineScope.runBlockingTest(block)
}
