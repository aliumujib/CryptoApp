package com.aliumujib.cryptoapp.walletdata

import com.aliumujib.cryptoapp.coremodels.Wallet
import com.aliumujib.cryptoapp.walletdata.cache.impl.WalletStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeWalletStore : WalletStore {

    private val wallet = mutableListOf<Wallet>()

    override suspend fun save(items: List<Wallet>) {
        wallet.addAll(items)
    }

    override fun stream(): Flow<List<Wallet>> {
        return flowOf(wallet)
    }

    override suspend fun isEmpty(): Boolean {
        return wallet.isEmpty()
    }

}