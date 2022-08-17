package com.aliumujib.cryptoapp.dashboard.domain

import app.cash.turbine.test
import com.aliumujib.cryptoapp.coremodels.WalletsWithExchangeRates
import com.aliumujib.cryptoapp.currencydatalib.domain.CurrenciesRepository
import com.aliumujib.cryptoapp.ratedatalib.domain.RatesRepository
import com.aliumujib.cryptoapp.sharedtestutils.CoroutineTest
import com.aliumujib.cryptoapp.sharedtestutils.RatesDummyData
import com.aliumujib.cryptoapp.sharedtestutils.TestPostExecutionThread
import com.aliumujib.cryptoapp.sharedtestutils.WalletsDummyData
import com.aliumujib.cryptoapp.walletdata.domain.WalletsRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class StreamWalletsAndExchangeRatesTest : CoroutineTest() {

    private val fakeWalletList = WalletsDummyData.generateFakeWalletList()
    private val fakeCurrencyList = WalletsDummyData.generateFakeCurrencyList(listOf("BUSD", "BNB"))
    private val fakeExchangeRates = RatesDummyData.generateDummyExchangeRates()

    private val postExecutionThread by lazy {
        TestPostExecutionThread()
    }

    @MockK
    private lateinit var streamWallets: StreamWallets
    @MockK
    private lateinit var fetchExchangeRates: FetchExchangeRates
    @MockK
    private lateinit var streamBaseFiat: StreamBaseFiat

    private lateinit var sut: StreamWalletsAndExchangeRates

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)

        sut = StreamWalletsAndExchangeRates(
            streamWallets,
            fetchExchangeRates,
            streamBaseFiat,
            postExecutionThread
        )
    }

    private fun stubSuccessfulCurrencyRepositoryResponse() {
        coEvery {
            fetchExchangeRates(any())
        } returns fakeExchangeRates
    }

    private fun stubSuccessfulWalletsRepositoryResponse() {
        coEvery {
            streamWallets.build()
        } returns flowOf(fakeWalletList)
    }

    private fun stubSuccessfulRatesRepositoryResponse() {
        coEvery {
            streamBaseFiat.build()
        } returns flowOf("USD")
    }

    private fun stubFailedWalletsRepositoryResponse() {
        coEvery {
            streamWallets.build()
        } returns flow {
            throw IllegalAccessException()
        }
    }

    @Test
    fun assert_StreamWalletsAndExchangeRates_returns_data_when_there_is_no_error() =
        coroutineScopedTest {
            // GIVEN
            stubSuccessfulCurrencyRepositoryResponse()
            stubSuccessfulRatesRepositoryResponse()
            stubSuccessfulWalletsRepositoryResponse()

            val expected = WalletsWithExchangeRates(
                wallets = fakeWalletList,
                exchangeRates = mapOf("BUSD" to 1.0, "BTC" to 1.0, "USDT" to 1.0, "BNB" to 1.0),
                "USD"
            )

            // THEN
            sut.build().test {
                assertThat(awaitItem()).isEqualTo(expected)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun assert_StreamWalletsAndExchangeRates_returns_error_when_there_is_an_error() =
        coroutineScopedTest {
            // GIVEN
            stubSuccessfulRatesRepositoryResponse()
            stubFailedWalletsRepositoryResponse()
            stubSuccessfulCurrencyRepositoryResponse()

            // THEN
            sut.build().test {
                assertThat(awaitError()).isInstanceOf(IllegalAccessException::class.java)
                cancelAndIgnoreRemainingEvents()
            }
        }
}
