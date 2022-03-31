package com.aliumujib.cryptoapp.dashboard.presentation.state

import com.aliumujib.cryptoapp.presentation.base.ViewState

data class HeaderResultState(
    val totalBalance: Double,
    val baseFiatCurrency: String?
)

sealed class HeaderViewState(
    val result: HeaderResultState,
    val showProgress: Boolean,
    val showEmpty: Boolean,
    val error: ErrorState
) : ViewState {
    object Initial : HeaderViewState(
        result = HeaderResultState(
            totalBalance = 0.0,
            baseFiatCurrency = null
        ),
        showProgress = false,
        showEmpty = false,
        error = ErrorState(
            showError = false,
            error = null
        )
    )

    object Loading : HeaderViewState(
        result = HeaderResultState(
            totalBalance = 0.0,
            baseFiatCurrency = null
        ),
        showProgress = true,
        showEmpty = false,
        error = ErrorState(
            showError = false,
            error = null
        )
    )

    data class Error(
        val message: String
    ) : HeaderViewState(
        result = HeaderResultState(
            totalBalance = 0.0,
            baseFiatCurrency = null
        ),
        showProgress = false,
        showEmpty = false,
        error = ErrorState(
            showError = true,
            error = message
        )
    )

    data class Loaded(
        val amount: Double,
        val baseFiatCurrency: String
    ) : HeaderViewState(
        result = HeaderResultState(
            totalBalance = amount,
            baseFiatCurrency = baseFiatCurrency
        ),
        showProgress = false,
        showEmpty = false,
        error = ErrorState(
            showError = true,
            error = null
        )
    )
}
