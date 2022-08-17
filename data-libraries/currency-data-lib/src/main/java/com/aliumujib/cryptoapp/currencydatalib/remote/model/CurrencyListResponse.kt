package com.aliumujib.cryptoapp.currencydatalib.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrencyListResponse(
    val currencies: List<CurrencyResponse>,
    val ok: Boolean,
    val total: Int
)

@JsonClass(generateAdapter = true)
data class CurrencyResponse(
    @Json(name = "blockchain_symbol")
    val blockchainSymbol: String,
    val code: String,
    @Json(name = "coin_id")
    val coinId: String,
    @Json(name = "colorful_image_url")
    val colorfulImageUrl: String,
    @Json(name = "contract_address")
    val contractAddress: String,
    @Json(name = "deposit_address_tag_name")
    val depositAddressTagName: String,
    @Json(name = "deposit_address_tag_type")
    val depositAddressTagType: String,
    @Json(name = "display_decimal")
    val displayDecimal: Int,
    val explorer: String,
    @Json(name = "gas_limit")
    val gasLimit: Int,
    @Json(name = "gray_image_url")
    val grayImageUrl: String,
    @Json(name = "has_deposit_address_tag")
    val hasDepositAddressTag: Boolean,
    @Json(name = "is_erc20")
    val isErc20: Boolean,
    @Json(name = "min_balance")
    val minBalance: Int,
    val name: String,
    @Json(name = "num_confirmation_required")
    val numConfirmationRequired: Int,
    @Json(name = "supports_legacy_address")
    val supportsLegacyAddress: Boolean,
    val symbol: String,
    @Json(name = "token_decimal")
    val tokenDecimal: Int,
    @Json(name = "token_decimal_value")
    val tokenDecimalValue: String,
    @Json(name = "trading_symbol")
    val tradingSymbol: String,
    @Json(name = "withdrawal_eta")
    val withdrawalEta: List<String>
)
