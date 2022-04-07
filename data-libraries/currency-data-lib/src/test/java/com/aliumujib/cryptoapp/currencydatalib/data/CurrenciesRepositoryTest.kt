package com.aliumujib.cryptoapp.currencydatalib.data

import app.cash.turbine.test
import com.aliumujib.cryptoapp.coremodels.Currency
import com.aliumujib.cryptoapp.currencydatalib.FakeCurrenciesSource
import com.aliumujib.cryptoapp.currencydatalib.FakeCurrenciesStore
import com.aliumujib.cryptoapp.currencydatalib.cache.impl.CurrencyStore
import com.aliumujib.cryptoapp.currencydatalib.domain.CurrenciesRepository
import com.aliumujib.cryptoapp.remote.datasource.DataSource
import com.aliumujib.cryptoapp.sharedtestutils.CoroutineTest
import com.aliumujib.cryptoapp.sharedtestutils.CurrencyDummyData
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CurrenciesRepositoryTest : CoroutineTest() {

    private lateinit var sut: CurrenciesRepository

    private val store: CurrencyStore = FakeCurrenciesStore()

    private val datasource: DataSource<List<Currency>> by lazy {
        FakeCurrenciesSource(CurrencyDummyData.generateFakeCurrencyList())
    }

    @Before
    fun setUp() {
        sut = CurrenciesRepositoryImpl(store, datasource)
    }

    @Test
    fun assert_that_streamCurrencies_emits_data_when_data_exists() = coroutineScopedTest {
        store.save(CurrencyDummyData.generateFakeCurrencyList())
        val expected = store.stream().first()

        sut.streamCurrencies().test {
            assertThat(expected).isEqualTo(awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun assert_that_streamBaseFiatCurrencyCode_emits_data() = coroutineScopedTest {
        sut.streamBaseFiatCurrencyCode().test {
            assertThat("USD").isEqualTo(awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
