package com.aliumujib.cryptoapp.cache.wallets.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.aliumujib.cryptoapp.cache.wallets.models.WalletCacheModel
import com.aliumujib.cryptoapp.cache.wallets.models.WalletWithCurrencyCacheModel
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WalletsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun saveWallets(wallets: List<WalletCacheModel>)

    @Transaction
    @Query("SELECT * FROM wallets")
    abstract fun streamWallets(): Flow<List<WalletWithCurrencyCacheModel>>

    @Query("SELECT COUNT(currency) FROM wallets")
    abstract suspend fun count(): Int
}
