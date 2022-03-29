package com.aliumujib.cryptoapp.cache.currencies.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aliumujib.cryptoapp.cache.currencies.models.CurrencyCacheModel
import com.aliumujib.cryptoapp.cache.rates.models.ExchangeRateCacheModel
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CurrenciesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun saveCurrencies(accounts: List<CurrencyCacheModel>)

    @Query("SELECT * FROM currencies")
    abstract fun streamCurrencies(): Flow<List<CurrencyCacheModel>>

    @Query("SELECT COUNT(coinId) FROM currencies")
    abstract suspend fun count(): Int
}
