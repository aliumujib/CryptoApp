package com.aliumujib.cryptoapp.cache.rates.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rates")
data class ExchangeRateCacheModel(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "fromCurrency") val fromCurrency: String,
    @ColumnInfo(name = "rates") val rates: List<RateCacheModel>,
    @ColumnInfo(name = "timeStamp") val timeStamp: Long,
    @ColumnInfo(name = "toCurrency") val toCurrency: String
)

data class RateCacheModel(
    val amount: Double,
    val rate: Double
)