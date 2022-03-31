package com.aliumujib.cryptoapp.uicommons

fun <K, V> Map<K, V>?.orEmpty(): Map<K, V> {
    return this ?: mutableMapOf<K, V>()
}