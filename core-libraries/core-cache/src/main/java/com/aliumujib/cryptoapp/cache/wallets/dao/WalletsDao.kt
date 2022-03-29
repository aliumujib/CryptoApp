package com.aliumujib.cryptoapp.cache.wallets.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.aliumujib.cryptoapp.cache.wallets.models.WalletCacheModel
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WalletsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun saveWallets(wallets: List<WalletCacheModel>)

    @Query("SELECT * FROM wallets")
    abstract fun streamWallets(): Flow<List<WalletCacheModel>>

    @Query("SELECT COUNT(currency) FROM wallets")
    abstract suspend fun count(): Int
}
