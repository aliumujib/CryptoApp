package com.aliumujib.cryptoapp.uicommons

val Throwable.errorMessage: String
    get() = message ?: localizedMessage ?: "An error occurred"
