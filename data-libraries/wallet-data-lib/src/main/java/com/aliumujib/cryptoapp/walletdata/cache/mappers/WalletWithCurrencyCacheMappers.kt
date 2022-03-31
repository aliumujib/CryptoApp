package com.aliumujib.cryptoapp.walletdata.cache.mappers

import com.aliumujib.cryptoapp.cache.mapper.CacheModelMapper
import com.aliumujib.cryptoapp.cache.wallets.models.WalletWithCurrencyCacheModel
import com.aliumujib.cryptoapp.coremodels.Wallet
import com.aliumujib.cryptoapp.currencydatalib.cache.mappers.CurrencyCacheMappers
import java.lang.UnsupportedOperationException
import javax.inject.Inject

class WalletWithCurrencyCacheMappers @Inject constructor(private val currencyCacheMappers: CurrencyCacheMappers) :
    CacheModelMapper<Wallet, WalletWithCurrencyCacheModel> {

    override fun mapToModel(entity: WalletWithCurrencyCacheModel): Wallet {
        return with(entity) {
            Wallet(
                entity.wallet.currency,
                currency.let {
                    currencyCacheMappers.mapToModel(it)
                },
                wallet.amount
            )
        }
    }

    override fun mapToEntity(model: Wallet): WalletWithCurrencyCacheModel {
        throw UnsupportedOperationException()
    }
}
