package com.aliumujib.cryptoapp.ratedatalib.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LiveRatesResponse(
    val ok: Boolean,
    val tiers: List<ExchangeRateResponse>,
    val warning: String
)

@JsonClass(generateAdapter = true)
data class ExchangeRateResponse(
    @Json(name = "from_currency")
    val fromCurrency: String,
    val rates: List<RateResponse>,
    @Json(name = "time_stamp")
    val timeStamp: Long,
    @Json(name = "to_currency")
    val toCurrency: String
)

@JsonClass(generateAdapter = true)
data class RateResponse(
    val amount: String,
    val rate: String
)
