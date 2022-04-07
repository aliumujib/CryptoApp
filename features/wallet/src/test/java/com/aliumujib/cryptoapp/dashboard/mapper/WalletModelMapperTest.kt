package com.aliumujib.cryptoapp.dashboard.mapper


import com.aliumujib.cryptoapp.coremodels.WalletsWithExchangeRates
import com.aliumujib.cryptoapp.sharedtestutils.WalletsDummyData
import com.google.common.truth.Truth.assertThat
import konveyor.base.randomBuild
import org.junit.Test

class WalletModelMapperTest {

    private val walletModelMapper = WalletModelMapper()

    @Test
    fun mapToModel() {
        val wallet = WalletsDummyData.generateFakeWallet("BNB")
        val walletUIModel = walletModelMapper.mapToModel(wallet)
        assertThat(wallet.amount).isEqualTo(walletUIModel.coinAmount)
    }

    @Test
    fun testMapToModel() {
        val wallet = WalletsWithExchangeRates(
            WalletsDummyData.generateFakeWalletList(amount = 2.0),
            hashMapOf("BNB" to 2.0, "USDT" to 1.0, "BUSD" to 1.5, "BTC" to 2.9),
            "USD"
        )
        val walletUIModels = walletModelMapper.mapToModel(wallet)
        assertThat(5.8).isEqualTo(walletUIModels[0].fiatAmount)
        assertThat(3.0).isEqualTo(walletUIModels[1].fiatAmount)
        assertThat(2.0).isEqualTo(walletUIModels[2].fiatAmount)
        assertThat(4.0).isEqualTo(walletUIModels[3].fiatAmount)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun mapToDomain() {
        walletModelMapper.mapToDomain(randomBuild())
    }

}