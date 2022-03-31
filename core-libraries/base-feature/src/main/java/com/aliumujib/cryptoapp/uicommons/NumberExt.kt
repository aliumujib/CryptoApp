package com.aliumujib.cryptoapp.uicommons

fun Double?.orZero(): Double {
    return this ?: 0.0
}
