package com.aliumujib.cryptoapp.ratedatalib.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LiveRatesResponse(
    val ok: Boolean,
    val tiers: List<ExchangeRateResponse>,
    val warning: String
)

@JsonClass(generateAdapter = true)
data class ExchangeRateResponse(
    val from_currency: String,
    val rates: List<RateResponse>,
    val time_stamp: Long,
    val to_currency: String
)

@JsonClass(generateAdapter = true)
data class RateResponse(
    val amount: String,
    val rate: String
)