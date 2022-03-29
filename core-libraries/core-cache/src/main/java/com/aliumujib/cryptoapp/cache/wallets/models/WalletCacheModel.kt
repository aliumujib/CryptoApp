package com.aliumujib.cryptoapp.cache.wallets.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.aliumujib.cryptoapp.cache.currencies.models.CurrencyCacheModel

@Entity(
    tableName = "wallets",
    foreignKeys = [
        ForeignKey(
            entity = CurrencyCacheModel::class,
            parentColumns = arrayOf("coinId"),
            childColumns = arrayOf("currency"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class WalletCacheModel(
    @PrimaryKey
    @ColumnInfo(name = "currency")
    val currency: String,

    @ColumnInfo(name = "amount")
    val amount: Double
)

