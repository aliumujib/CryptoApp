package com.aliumujib.cryptoapp.walletdata.di

import android.content.Context
import com.aliumujib.cryptoapp.cache.wallets.dao.WalletsDao
import com.aliumujib.cryptoapp.walletdata.cache.impl.WalletStore
import com.aliumujib.cryptoapp.walletdata.cache.impl.WalletStoreImpl
import com.aliumujib.cryptoapp.walletdata.cache.mappers.WalletCacheMappers
import com.aliumujib.cryptoapp.walletdata.cache.mappers.WalletWithCurrencyCacheMappers
import com.aliumujib.cryptoapp.walletdata.data.WalletsRepositoryImpl
import com.aliumujib.cryptoapp.walletdata.domain.WalletsRepository
import com.aliumujib.cryptoapp.walletdata.remote.WalletDataSource
import com.aliumujib.cryptoapp.walletdata.remote.WalletDataSourceImpl
import com.aliumujib.cryptoapp.walletdata.remote.mappers.WalletRemoteMappers
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
interface WalletDataModule {

    @Binds
    @Singleton
    fun bindRepository(repository: WalletsRepositoryImpl): WalletsRepository

}

@Module
@InstallIn(SingletonComponent::class)
object WalletDataProviders {

    @Provides
    @Singleton
    fun providesStore(
        walletsDao: WalletsDao,
        walletWithCurrencyCacheMappers: WalletWithCurrencyCacheMappers,
        walletMappers: WalletCacheMappers
    ): WalletStore {
        return WalletStoreImpl(walletsDao, walletMappers, walletWithCurrencyCacheMappers)
    }

    @Provides
    @Singleton
    fun providesDataSource(
        moshi: Moshi,
        walletRemoteMappers: WalletRemoteMappers,
        @ApplicationContext context: Context
    ): WalletDataSource {
        return WalletDataSourceImpl(moshi, walletRemoteMappers, context)
    }

}
