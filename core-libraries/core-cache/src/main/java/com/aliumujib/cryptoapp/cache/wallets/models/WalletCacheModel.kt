package com.aliumujib.cryptoapp.cache.wallets.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.aliumujib.cryptoapp.cache.currencies.models.CurrencyCacheModel

@Entity(tableName = "wallets")
data class WalletCacheModel(
    @PrimaryKey
    @ColumnInfo(name = "currency")
    val currency: String,

    @ColumnInfo(name = "amount")
    val amount: Double
)

