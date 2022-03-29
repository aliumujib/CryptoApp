package com.aliumujib.cryptoapp.cache.di

import android.content.Context
import com.aliumujib.cryptoapp.cache.currencies.dao.CurrenciesDao
import com.aliumujib.cryptoapp.cache.rates.dao.ExchangeRatesDao
import com.aliumujib.cryptoapp.cache.room.CryptoDatabase
import com.aliumujib.cryptoapp.cache.wallets.dao.WalletsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
internal object CacheModule {

    @[Provides Singleton]
    fun provideDatabase(@ApplicationContext context: Context): CryptoDatabase {
        return CryptoDatabase.create(context)
    }

    @[Provides Singleton]
    fun provideExchangeRatesDao(cryptoDatabase: CryptoDatabase): ExchangeRatesDao {
        return cryptoDatabase.exchangeRatesDao()
    }

    @[Provides Singleton]
    fun provideWalletsDao(cryptoDatabase: CryptoDatabase): WalletsDao {
        return cryptoDatabase.walletsDao()
    }

    @[Provides Singleton]
    fun provideCurrenciesDao(cryptoDatabase: CryptoDatabase): CurrenciesDao {
        return cryptoDatabase.currenciesDao()
    }
}
