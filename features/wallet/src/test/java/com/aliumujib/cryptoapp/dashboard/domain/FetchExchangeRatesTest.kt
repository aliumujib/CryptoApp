package com.aliumujib.cryptoapp.dashboard.domain

import com.aliumujib.cryptoapp.coredomain.utils.PostExecutionThread
import com.aliumujib.cryptoapp.coremodels.ExchangeRate
import com.aliumujib.cryptoapp.sharedtestutils.CoroutineTest
import com.aliumujib.cryptoapp.sharedtestutils.TestPostExecutionThread
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class FetchExchangeRatesTest : CoroutineTest() {

    private lateinit var sut: FetchExchangeRates

    private val rates: List<ExchangeRate> = DummyData.generateFakeExchangeRateList(
        listOf(
            Triple("BTC", "USD", 64000.0),
            Triple("USDT", "USD", 1.1),
            Triple("USDC", "USD", 1.0),
            Triple("BNB", "USD", 436.0)
        )
    )
    private val postExecutionThread: PostExecutionThread by lazy {
        TestPostExecutionThread()
    }
    private val repository: FakeRatesRepository by lazy {
        FakeRatesRepository(rates)
    }

    @Before
    fun setUp() {
        sut = FetchExchangeRates(repository, postExecutionThread)
    }

    @Test
    fun check_that_fetchExchangeRates_returns_average_exchange_rate_for_coin_pair() =
        coroutineScopedTest {
            repository.responseType = ResponseType.DATA
            val expected =
                hashMapOf("USDT" to 1.1, "BTC" to 64000.0, "BNB" to 436.0)
            val actual =
                sut.invoke(FetchExchangeRates.Params.make(listOf("USDT", "BTC", "BNB"), "USD"))
            assertThat(expected).isEqualTo(actual)
        }


    @Test
    fun check_that_fetchExchangeRates_returns_empty_for_invalid_coin_pair() =
        coroutineScopedTest {
            repository.responseType = ResponseType.DATA
            val expected = hashMapOf<String, Double>()
            val actual =
                sut.invoke(FetchExchangeRates.Params.make(listOf("BNAS", "DSSD", "ADDE"), "USD"))
            assertThat(expected).isEqualTo(actual)
        }


}
