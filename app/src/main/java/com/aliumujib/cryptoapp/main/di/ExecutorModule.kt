package com.aliumujib.cryptoapp.main.di

import com.aliumujib.cryptoapp.coredomain.utils.PostExecutionThread
import com.aliumujib.cryptoapp.data.flow.PostExecutionThreadImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface ExecutorModule {

    @get:[Binds Singleton]
    val PostExecutionThreadImpl.postExecutionThread: PostExecutionThread
}
