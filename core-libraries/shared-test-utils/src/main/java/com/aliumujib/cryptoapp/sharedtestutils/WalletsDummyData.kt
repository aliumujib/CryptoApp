package com.aliumujib.cryptoapp.sharedtestutils

import com.aliumujib.cryptoapp.coremodels.Currency
import com.aliumujib.cryptoapp.coremodels.Wallet
import konveyor.base.randomBuild
import kotlin.random.Random

object WalletsDummyData {

    fun generateFakeCurrency(coinId: String): Currency {
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

    fun generateFakeCurrencyList(list: List<String>): List<Currency> {
        return list.map {
            generateFakeCurrency(it)
        }
    }

    fun generateFakeWallet(coinId: String, amount: Double = 1.0): Wallet {
        return Wallet(coinId, generateFakeCurrency(coinId), amount)
    }

    fun generateFakeWalletList(amount: Double = 1.0): List<Wallet> {
        return listOf(
            generateFakeWallet("BTC", amount),
            generateFakeWallet("BUSD", amount),
            generateFakeWallet("USDT", amount),
            generateFakeWallet("BNB", amount)
        )
    }
}
