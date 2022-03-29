package com.aliumujib.cryptoapp.coremodels

data class Currency(
    val blockchainSymbol: String,
    val code: String,
    val coinId: String,
    val colorfulImageUrl: String,
    val contractAddress: String,
    val depositAddressTagName: String,
    val depositAddressTagType: String,
    val displayDecimal: Int,
    val explorer: String,
    val gasLimit: Int,
    val grayImageUrl: String,
    val hasDepositAddressTag: Boolean,
    val isErc20: Boolean,
    val minBalance: Int,
    val name: String,
    val numConfirmationRequired: Int,
    val supportsLegacyAddress: Boolean,
    val symbol: String,
    val tokenDecimal: Int,
    val tokenDecimalValue: String,
    val tradingSymbol: String,
    val withdrawalEta: List<String>
)
