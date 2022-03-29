package com.aliumujib.cryptoapp.cache.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aliumujib.cryptoapp.cache.currencies.dao.CurrenciesDao
import com.aliumujib.cryptoapp.cache.currencies.models.CurrencyCacheModel
import com.aliumujib.cryptoapp.cache.rates.dao.ExchangeRatesDao
import com.aliumujib.cryptoapp.cache.rates.models.ExchangeRateCacheModel
import com.aliumujib.cryptoapp.cache.rates.models.RateCacheModel
import com.aliumujib.cryptoapp.cache.wallets.dao.WalletsDao
import com.aliumujib.cryptoapp.cache.wallets.models.WalletCacheModel

@Database(
    entities = [
        WalletCacheModel::class,
        CurrencyCacheModel::class,
        ExchangeRateCacheModel::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class CryptoDatabase : RoomDatabase() {

    abstract fun exchangeRatesDao(): ExchangeRatesDao

    abstract fun walletsDao(): WalletsDao

    abstract fun currenciesDao(): CurrenciesDao

    companion object {
        private const val DATABASE_NAME: String = "crypto_db"
        fun create(context: Context): CryptoDatabase = Room.databaseBuilder(
            context.applicationContext,
            CryptoDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}
