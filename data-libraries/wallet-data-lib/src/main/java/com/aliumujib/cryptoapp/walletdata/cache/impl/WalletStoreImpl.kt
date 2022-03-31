package com.aliumujib.cryptoapp.walletdata.cache.impl

import com.aliumujib.cryptoapp.cache.contract.Store
import com.aliumujib.cryptoapp.cache.wallets.dao.WalletsDao
import com.aliumujib.cryptoapp.cache.wallets.models.WalletWithCurrencyCacheModel
import com.aliumujib.cryptoapp.coremodels.Wallet
import com.aliumujib.cryptoapp.walletdata.cache.mappers.WalletCacheMappers
import com.aliumujib.cryptoapp.walletdata.cache.mappers.WalletWithCurrencyCacheMappers
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

typealias WalletStore = Store<Wallet>

class WalletStoreImpl @Inject constructor(
    private val walletsDao: WalletsDao,
    private val walletMappers: WalletCacheMappers,
    private val walletWithCurrencyCacheMappers: WalletWithCurrencyCacheMappers
) : WalletStore {

    override suspend fun save(items: List<Wallet>) {
        walletsDao.saveWallets(items.map(walletMappers::mapToEntity))
    }

    override fun stream(): Flow<List<Wallet>> {
        return walletsDao.streamWallets().map(this::mapWalletsToModel)
    }

    override suspend fun isEmpty(): Boolean {
        return walletsDao.count() == 0
    }

    private fun mapWalletsToModel(walletList: List<WalletWithCurrencyCacheModel>): List<Wallet> {
        return walletList.map {
            walletWithCurrencyCacheMappers.mapToModel(it)
        }
    }
}
