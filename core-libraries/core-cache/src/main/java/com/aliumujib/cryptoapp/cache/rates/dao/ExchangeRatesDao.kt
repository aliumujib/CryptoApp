package com.aliumujib.cryptoapp.cache.rates.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aliumujib.cryptoapp.cache.rates.models.ExchangeRateCacheModel
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ExchangeRatesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveExchanges(accounts: List<ExchangeRateCacheModel>)

    @Query("SELECT * FROM rates")
    abstract fun streamExchangeRates(): Flow<List<ExchangeRateCacheModel>>

    @Query("SELECT * FROM rates WHERE fromCurrency=:from AND toCurrency=:to")
    abstract suspend fun fetchExchangeRateForPair(
        from: String,
        to: String
    ): ExchangeRateCacheModel?

    @Query("SELECT COUNT(id) FROM rates")
    abstract suspend fun count(): Int
}
