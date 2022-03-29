package com.aliumujib.cryptoapp.cache.rates.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rates")
data class ExchangeRateCacheModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "fromCurrency") val fromCurrency: String,
    @ColumnInfo(name = "rate") val rate: RateCacheModel,
    @ColumnInfo(name = "timeStamp") val timeStamp: Int,
    @ColumnInfo(name = "toCurrency") val toCurrency: String
)

data class RateCacheModel(
    val amount: String,
    val rate: String
)