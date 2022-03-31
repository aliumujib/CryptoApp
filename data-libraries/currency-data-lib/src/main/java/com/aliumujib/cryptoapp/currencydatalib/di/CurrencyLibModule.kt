package com.aliumujib.cryptoapp.currencydatalib.di

import android.content.Context
import com.aliumujib.cryptoapp.cache.currencies.dao.CurrenciesDao
import com.aliumujib.cryptoapp.currencydatalib.cache.impl.CurrencyStore
import com.aliumujib.cryptoapp.currencydatalib.cache.impl.CurrencyStoreImpl
import com.aliumujib.cryptoapp.currencydatalib.cache.mappers.CurrencyCacheMappers
import com.aliumujib.cryptoapp.currencydatalib.data.CurrenciesRepositoryImpl
import com.aliumujib.cryptoapp.currencydatalib.domain.CurrenciesRepository
import com.aliumujib.cryptoapp.currencydatalib.remote.impl.CurrencyDataSource
import com.aliumujib.cryptoapp.currencydatalib.remote.impl.CurrencyDataSourceImpl
import com.aliumujib.cryptoapp.currencydatalib.remote.mappers.CurrencyRemoteMappers
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
    fun providesDataSource(
        moshi: Moshi,
        mappers: CurrencyRemoteMappers,
        @ApplicationContext context: Context
    ): CurrencyDataSource {
        return CurrencyDataSourceImpl(moshi, mappers, context)
    }
}
