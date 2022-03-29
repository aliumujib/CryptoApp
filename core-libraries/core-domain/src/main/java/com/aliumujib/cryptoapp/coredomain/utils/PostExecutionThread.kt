package com.aliumujib.cryptoapp.coredomain.utils

import kotlinx.coroutines.CoroutineDispatcher

interface PostExecutionThread {

    val ui: CoroutineDispatcher

    val io: CoroutineDispatcher

    val default: CoroutineDispatcher
}
