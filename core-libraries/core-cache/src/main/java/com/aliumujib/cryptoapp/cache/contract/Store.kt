package com.aliumujib.cryptoapp.cache.contract

import kotlinx.coroutines.flow.Flow

interface Store<T> {
    suspend fun save(items: List<T>)

    fun stream(): Flow<List<T>>

    suspend fun isEmpty(): Boolean
}