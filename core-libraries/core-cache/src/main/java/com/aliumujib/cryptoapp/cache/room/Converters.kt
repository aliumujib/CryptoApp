package com.aliumujib.cryptoapp.cache.room

import androidx.room.TypeConverter
import com.aliumujib.cryptoapp.cache.rates.models.RateCacheModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private var gson = Gson()

    @TypeConverter
    fun fromStringList(value: List<String>?): String? {
        return if (value == null) null else gson.toJson(value)
    }

    @TypeConverter
    fun toStringList(data: String?): List<String>? {
        val avatarType = object : TypeToken<List<String>>() {
        }.type
        return gson.fromJson(data, avatarType)
    }

    @TypeConverter
    fun toRateCacheModelList(data: String?): List<RateCacheModel>? {
        val addressType = object : TypeToken<List<RateCacheModel>>() {
        }.type
        return gson.fromJson(data, addressType)
    }

    @TypeConverter
    fun fromRateCacheModelList(value: List<RateCacheModel>?): String? {
        return if (value == null) null else gson.toJson(value)
    }
}
