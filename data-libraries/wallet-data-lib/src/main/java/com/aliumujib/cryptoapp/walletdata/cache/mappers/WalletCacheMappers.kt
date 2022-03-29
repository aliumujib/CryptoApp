package com.aliumujib.cryptoapp.walletdata.cache.mappers

import com.aliumujib.android.currencydatalib.domain.HighSpeedCurrencyCache
import com.aliumujib.cryptoapp.cache.mapper.CacheModelMapper
import com.aliumujib.cryptoapp.cache.wallets.models.WalletCacheModel
import com.aliumujib.cryptoapp.coremodels.Wallet
import javax.inject.Inject

class WalletCacheMappers @Inject constructor(private val highSpeedCurrencyCache: HighSpeedCurrencyCache) :
    CacheModelMapper<Wallet, WalletCacheModel> {

    override fun mapToModel(entity: WalletCacheModel): Wallet {
        return with(entity) {
            Wallet(
                highSpeedCurrencyCache[currency],
                amount
            )
        }
    }

    override fun mapToEntity(model: Wallet): WalletCacheModel {
        return with(model) {
            WalletCacheModel(
                currency?.coinId.orEmpty(),
                amount
            )
        }
    }

}