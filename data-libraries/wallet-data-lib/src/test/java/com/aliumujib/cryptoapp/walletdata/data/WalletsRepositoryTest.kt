package com.aliumujib.cryptoapp.walletdata.data

import app.cash.turbine.test
import com.aliumujib.cryptoapp.coremodels.Wallet
import com.aliumujib.cryptoapp.remote.datasource.DataSource
import com.aliumujib.cryptoapp.sharedtestutils.CoroutineTest
import com.aliumujib.cryptoapp.walletdata.DummyData
import com.aliumujib.cryptoapp.walletdata.FakeWalletSource
import com.aliumujib.cryptoapp.walletdata.FakeWalletStore
import com.aliumujib.cryptoapp.walletdata.cache.impl.WalletStore
import com.aliumujib.cryptoapp.walletdata.domain.WalletsRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class WalletsRepositoryTest : CoroutineTest() {

    private lateinit var sut: WalletsRepository

    private val store: WalletStore = FakeWalletStore()

    private val wallets: List<Wallet> by lazy {
        DummyData.generateFakeWalletList()
    }

    private val datasource: DataSource<List<Wallet>> by lazy {
        FakeWalletSource(wallets)
    }

    @Before
    fun setUp() {
        sut = WalletsRepositoryImpl(store, datasource)
    }

    @Test
    fun assert_that_streamCurrencies_emits_data_when_data_exists() = coroutineScopedTest {
        sut.streamWallets().test {
            assertThat(wallets).isEqualTo(awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
