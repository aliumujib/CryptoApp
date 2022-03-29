package com.aliumujib.cryptoapp.walletdata.data

import com.aliumujib.cryptoapp.coremodels.Wallet
import com.aliumujib.cryptoapp.data.flow.networkBoundResource
import com.aliumujib.cryptoapp.walletdata.cache.impl.WalletStore
import com.aliumujib.cryptoapp.walletdata.domain.WalletsRepository
import com.aliumujib.cryptoapp.walletdata.remote.WalletDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WalletsRepositoryImpl @Inject constructor(
    private val walletStore: WalletStore,
    private val walletDataSource: WalletDataSource
) : WalletsRepository {

    override fun streamWallets(): Flow<List<Wallet>> {
        return networkBoundResource(queryCache = {
            walletStore.stream()
        }, fetchRemote = {
            walletDataSource.fetch()
        }, saveFetchResult = {
            it?.let {
                walletStore.save(it)
            }
        }, shouldFetch = {
            true
        }, onFetchFailed = {
            throw it
        })
    }

}