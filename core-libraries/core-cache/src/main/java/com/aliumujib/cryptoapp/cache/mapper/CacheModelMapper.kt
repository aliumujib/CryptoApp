package com.aliumujib.cryptoapp.cache.mapper

interface CacheModelMapper<M, E> {

    fun mapToModel(entity: E): M

    fun mapToEntity(model: M): E

}
