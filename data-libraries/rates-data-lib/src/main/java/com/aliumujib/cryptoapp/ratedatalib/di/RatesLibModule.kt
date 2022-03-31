package com.aliumujib.cryptoapp.ratedatalib.di

import android.content.Context
import com.aliumujib.cryptoapp.cache.rates.dao.ExchangeRatesDao
import com.aliumujib.cryptoapp.ratedatalib.cache.impl.RatesStore
import com.aliumujib.cryptoapp.ratedatalib.cache.impl.RatesStoreImpl
import com.aliumujib.cryptoapp.ratedatalib.cache.mappers.ExchangeRateCacheMappers
import com.aliumujib.cryptoapp.ratedatalib.data.RatesRepositoryImpl
import com.aliumujib.cryptoapp.ratedatalib.domain.RatesRepository
import com.aliumujib.cryptoapp.ratedatalib.remote.impl.RatesDataSource
import com.aliumujib.cryptoapp.ratedatalib.remote.impl.RatesDataSourceImpl
import com.aliumujib.cryptoapp.ratedatalib.remote.mappers.RateRemoteMappers
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
    fun bindRepository(repository: RatesRepositoryImpl): RatesRepository
}

@Module
@InstallIn(SingletonComponent::class)
object CurrencyDataProviders {

    @Provides
    @Singleton
    fun providesStore(exchangeRatesDao: ExchangeRatesDao, ratesCacheMappers: ExchangeRateCacheMappers): RatesStore {
        return RatesStoreImpl(exchangeRatesDao, ratesCacheMappers)
    }

    @Provides
    @Singleton
    fun providesDataSource(
        moshi: Moshi,
        mappers: RateRemoteMappers,
        @ApplicationContext context: Context
    ): RatesDataSource {
        return RatesDataSourceImpl(moshi, mappers, context)
    }
}
