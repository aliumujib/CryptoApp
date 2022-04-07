package com.aliumujib.cryptoapp.dashboard.presentation

import app.cash.turbine.test
import com.aliumujib.cryptoapp.currencydatalib.domain.CurrenciesRepository
import com.aliumujib.cryptoapp.dashboard.domain.FetchExchangeRates
import com.aliumujib.cryptoapp.dashboard.domain.StreamBaseFiat
import com.aliumujib.cryptoapp.dashboard.domain.StreamWallets
import com.aliumujib.cryptoapp.dashboard.domain.StreamWalletsAndExchangeRates
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
class WalletScreenIntentProcessorTest : CoroutineTest() {

    @MockK
    private lateinit var walletsRepository: WalletsRepository

    @MockK
    private lateinit var currenciesRepository: CurrenciesRepository

    @MockK
    private lateinit var ratesRepository: RatesRepository

    private val postExecutionThread by lazy {
        TestPostExecutionThread()
    }

    private lateinit var streamWallets: StreamWallets
    private lateinit var fetchExchangeRates: FetchExchangeRates
    private lateinit var streamBaseFiat: StreamBaseFiat
    private lateinit var streamWalletsAndExchangeRates: StreamWalletsAndExchangeRates

    private lateinit var sut: WalletScreenIntentProcessor

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)

        streamWallets = StreamWallets(walletsRepository, currenciesRepository, postExecutionThread)
        fetchExchangeRates = FetchExchangeRates(ratesRepository, postExecutionThread)
        streamBaseFiat = StreamBaseFiat(currenciesRepository, postExecutionThread)
        streamWalletsAndExchangeRates = StreamWalletsAndExchangeRates(
            streamWallets,
            fetchExchangeRates,
            streamBaseFiat,
            postExecutionThread
        )
        sut = WalletScreenIntentProcessor(streamWalletsAndExchangeRates)

    }

    private fun stubSuccessfulCurrencyRepositoryResponse() {
        coEvery {
            currenciesRepository.streamCurrencies()
        } returns flowOf(WalletsDummyData.generateFakeCurrencyList(listOf("BUSD", "BNB")))

        coEvery {
            currenciesRepository.streamBaseFiatCurrencyCode()
        } returns flowOf("USD")
    }

    private fun stubSuccessfulWalletsRepositoryResponse() {
        coEvery {
            walletsRepository.streamWallets()
        } returns flowOf(WalletsDummyData.generateFakeWalletList())
    }

    private fun stubSuccessfulRatesRepositoryResponse() {
        coEvery {
            ratesRepository.fetchRateForPair(any(), any())
        } returns RatesDummyData.generateExchangeRate("BUSD", "USD", 1.0)
    }

    private fun stubFailedWalletsRepositoryResponse() {
        coEvery {
            walletsRepository.streamWallets()
        } returns flow {
            throw IllegalAccessException()
        }
    }


    @Test
    fun assert_that_FetchWalletsIntent_returns_LoadedWalletsResult_when_there_is_no_error() =
        coroutineScopedTest {
            stubSuccessfulRatesRepositoryResponse()
            stubSuccessfulWalletsRepositoryResponse()
            stubSuccessfulCurrencyRepositoryResponse()

            sut.intentToResult(FetchWalletsIntent).test {
                assertThat(awaitItem()).isEqualTo(WalletScreenResult.LoadWalletsResult.Loading)
                assertThat(awaitItem()).isInstanceOf(WalletScreenResult.LoadWalletsResult.LoadedWalletsResult::class.java)
                cancelAndIgnoreRemainingEvents()
            }

        }


    @Test
    fun assert_that_FetchWalletsIntent_returns_LoadingError_when_there_is_an_error() =
        coroutineScopedTest {
            stubSuccessfulRatesRepositoryResponse()
            stubFailedWalletsRepositoryResponse()
            stubSuccessfulCurrencyRepositoryResponse()

            sut.intentToResult(FetchWalletsIntent).test {
                assertThat(awaitItem()).isEqualTo(WalletScreenResult.LoadWalletsResult.Loading)
                assertThat(awaitItem()).isInstanceOf(WalletScreenResult.LoadWalletsResult.LoadingError::class.java)
                cancelAndIgnoreRemainingEvents()
            }

        }

}