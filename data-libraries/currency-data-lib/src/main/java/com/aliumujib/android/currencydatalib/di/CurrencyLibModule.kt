package com.aliumujib.android.currencydatalib.di

import android.content.Context
import com.aliumujib.android.currencydatalib.cache.impl.CurrencyStore
import com.aliumujib.android.currencydatalib.cache.impl.CurrencyStoreImpl
import com.aliumujib.android.currencydatalib.cache.mappers.CurrencyCacheMappers
import com.aliumujib.android.currencydatalib.data.CurrenciesRepositoryImpl
import com.aliumujib.android.currencydatalib.domain.CurrenciesRepository
import com.aliumujib.android.currencydatalib.domain.HighSpeedCurrencyCache
import com.aliumujib.android.currencydatalib.remote.impl.CurrencyDataSource
import com.aliumujib.android.currencydatalib.remote.impl.CurrencyDataSourceImpl
import com.aliumujib.android.currencydatalib.remote.mappers.CurrencyRemoteMappers
import com.aliumujib.cryptoapp.cache.currencies.dao.CurrenciesDao
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CurrencyDataModule {

    @Binds
    @Singleton
    fun bindRepository(repository: CurrenciesRepositoryImpl): CurrenciesRepository

}

@Module
@InstallIn(SingletonComponent::class)
object CurrencyDataProviders {

    @Provides
    @Singleton
    fun providesStore(currenciesDao: CurrenciesDao, cacheMappers: CurrencyCacheMappers): CurrencyStore {
        return CurrencyStoreImpl(currenciesDao, cacheMappers)
    }

    @Provides
    @Singleton
    fun providesCache(): HighSpeedCurrencyCache {
        return hashMapOf()
    }

    @Provides
    @Singleton
    fun providesDataSource(
        moshi: Moshi,
        mappers: CurrencyRemoteMappers,
        @ApplicationContext context: Context
    ): CurrencyDataSource {
        return CurrencyDataSourceImpl(moshi, mappers, context)
    }

}
