package com.aliumujib.cryptoapp.data.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

fun <ResultType, RequestType> networkBoundResource(
    queryCache: () -> Flow<ResultType>,
    fetchRemote: suspend () -> RequestType,
    saveFetchResult: suspend (RequestType) -> Unit,
    shouldFetch: (ResultType) -> Boolean = { true },
    onFetchFailed: (Throwable) -> Unit = { Unit },
): Flow<ResultType> = flow {
    val cachedData = queryCache().first()
    val flow = if (shouldFetch(cachedData)) {
        try {
            if (cachedData != null) {
                if ((cachedData as? List<*>)?.size != 0) {
                    emit(cachedData)
                }else if(cachedData !is List<*>){
                    emit(cachedData)
                }
            }
            val fetchResponse = fetchRemote()
            saveFetchResult(fetchResponse)
            queryCache()
        } catch (throwable: Throwable) {
            onFetchFailed(throwable)
            queryCache()
        }
    } else {
        queryCache()
    }
    emitAll(flow)
}

