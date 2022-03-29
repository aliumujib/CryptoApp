package com.aliumujib.cryptoapp.walletdata.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WalletListResponse(
    val ok: Boolean,
    val wallet: List<WalletResponse>?,
    val warning: String
)

@JsonClass(generateAdapter = true)
data class WalletResponse(
    val amount: Double,
    val currency: String
)