package com.aliumujib.cryptoapp.data.flow

import com.aliumujib.cryptoapp.coredomain.utils.PostExecutionThread
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class PostExecutionThreadImpl @Inject constructor() : PostExecutionThread {

    override val ui: CoroutineDispatcher = Dispatchers.Main
    override val io: CoroutineDispatcher = Dispatchers.IO
    override val default: CoroutineDispatcher = Dispatchers.Default
}
