package com.aliumujib.cryptoapp.ratedatalib.data

import com.aliumujib.cryptoapp.coremodels.ExchangeRate
import com.aliumujib.cryptoapp.ratedatalib.DummyData
import com.aliumujib.cryptoapp.ratedatalib.FakeRatesSource
import com.aliumujib.cryptoapp.ratedatalib.FakeRatesStore
import com.aliumujib.cryptoapp.ratedatalib.cache.impl.RatesStore
import com.aliumujib.cryptoapp.ratedatalib.domain.RatesRepository
import com.aliumujib.cryptoapp.remote.datasource.DataSource
import com.aliumujib.cryptoapp.sharedtestutils.CoroutineTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RatesRepositoryTest : CoroutineTest() {

    private lateinit var sut: RatesRepository

    private val store: RatesStore = FakeRatesStore()

    private val datasource: DataSource<List<ExchangeRate>> by lazy {
        FakeRatesSource(DummyData.generateFakeExchangeList())
    }

    @Before
    fun setUp() {
        sut = RatesRepositoryImpl(store, datasource)
    }

    @Test
    fun assert_that_fetchRateForPair_returns_data_when_data_exists() = coroutineScopedTest {
        store.save(DummyData.generateFakeExchangeList())
        val expected = store.fetchRateForPair("BUSD", "USD")

        val actual = sut.fetchRateForPair("BUSD", "USD")
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun assert_that_fetchRateForPair_returns_null_when_data_does_not_exist() = coroutineScopedTest {
        val expected = null
        val actual = sut.fetchRateForPair("BSSUSD", "USD")
        assertThat(actual).isEqualTo(expected)
    }
}
