package com.aliumujib.cryptoapp.cache.wallets.models

import androidx.room.Embedded
import androidx.room.Relation
import com.aliumujib.cryptoapp.cache.currencies.models.CurrencyCacheModel

data class WalletWithCurrencyCacheModel(
    @Embedded val wallet: WalletCacheModel,
    @Relation(parentColumn = "currency", entityColumn = "coinId", entity = CurrencyCacheModel::class)
    val currency: CurrencyCacheModel
)

