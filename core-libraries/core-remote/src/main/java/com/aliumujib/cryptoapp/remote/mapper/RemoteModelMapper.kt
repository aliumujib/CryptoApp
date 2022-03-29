package com.aliumujib.cryptoapp.remote.mapper

interface RemoteModelMapper<M, R> {

    fun mapToModel(response: R): M

}
