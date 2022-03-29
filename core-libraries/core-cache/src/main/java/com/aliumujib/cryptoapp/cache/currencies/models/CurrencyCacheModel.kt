package com.aliumujib.cryptoapp.cache.currencies.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currencies")
data class CurrencyCacheModel(
    @ColumnInfo(name = "blockchainSymbol") val blockchainSymbol: String,
    @ColumnInfo(name = "code") val code: String,
    @[PrimaryKey ColumnInfo(name = "coinId")] val coinId: String,
    @ColumnInfo(name = "colorfulImageUrl") val colorfulImageUrl: String,
    @ColumnInfo(name = "contractAddress") val contractAddress: String,
    @ColumnInfo(name = "depositAddressTagName") val depositAddressTagName: String,
    @ColumnInfo(name = "depositAddressTagType") val depositAddressTagType: String,
    @ColumnInfo(name = "displayDecimal") val displayDecimal: Int,
    @ColumnInfo(name = "explorer") val explorer: String,
    @ColumnInfo(name = "gasLimit") val gasLimit: Int,
    @ColumnInfo(name = "grayImageUrl") val grayImageUrl: String,
    @ColumnInfo(name = "hasDepositAddressTag") val hasDepositAddressTag: Boolean,
    @ColumnInfo(name = "isErc20") val isErc20: Boolean,
    @ColumnInfo(name = "minBalance") val minBalance: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "numConfirmationRequired") val numConfirmationRequired: Int,
    @ColumnInfo(name = "supportsLegacyAddress") val supportsLegacyAddress: Boolean,
    @ColumnInfo(name = "symbol") val symbol: String,
    @ColumnInfo(name = "tokenDecimal") val tokenDecimal: Int,
    @ColumnInfo(name = "tokenDecimalValue") val tokenDecimalValue: String,
    @ColumnInfo(name = "tradingSymbol") val tradingSymbol: String,
    @ColumnInfo(name = "withdrawalEta") val withdrawalEta: List<String>
)
