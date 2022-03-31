package com.aliumujib.cryptoapp.walletdata

import com.aliumujib.cryptoapp.coremodels.Currency
import com.aliumujib.cryptoapp.coremodels.Wallet
import konveyor.base.randomBuild
import kotlin.random.Random

object DummyData {

    private fun generateFakeCurrency(coinId: String): Currency {
        return Currency(
            coinId,
            coinId,
            coinId,
            randomBuild(),
            randomBuild(),
            randomBuild(),
            randomBuild(),
            Random.nextInt(),
            randomBuild(),
            Random.nextInt(),
            randomBuild(),
            randomBuild(),
            randomBuild(),
            Random.nextInt(),
            randomBuild(),
            Random.nextInt(),
            randomBuild(),
            randomBuild(),
            Random.nextInt(),
            randomBuild(),
            randomBuild(),
            listOf(randomBuild(), randomBuild())
        )
    }

    private fun generateFakeWallet(coinId: String): Wallet{
        return Wallet(coinId, generateFakeCurrency(coinId), Random.nextDouble())
    }

    fun generateFakeWalletList(): List<Wallet> {
        return listOf(
            generateFakeWallet("BTC"),
            generateFakeWallet("BUSD"),
            generateFakeWallet("USDT"),
            generateFakeWallet("BNB")
        )
    }

}