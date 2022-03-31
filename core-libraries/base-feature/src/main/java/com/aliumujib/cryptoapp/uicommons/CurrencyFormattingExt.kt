package com.aliumujib.cryptoapp.uicommons

import java.text.NumberFormat
import java.util.Currency

fun Double.formatToCurrencyString(currencyCode: String): String {
    val formatter: NumberFormat = NumberFormat.getCurrencyInstance()
    formatter.maximumFractionDigits = 0
    formatter.currency = Currency.getInstance(currencyCode)
    return formatter.format(this)
}
