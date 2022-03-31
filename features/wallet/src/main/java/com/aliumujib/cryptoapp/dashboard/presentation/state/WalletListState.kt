package com.aliumujib.cryptoapp.dashboard.presentation.state

import com.aliumujib.cryptoapp.dashboard.presentation.model.WalletUIModel
import com.aliumujib.cryptoapp.presentation.base.ViewState

data class WalletResultState(
    val data: List<WalletUIModel>,
    val showResult: Boolean
)

data class ErrorState(
    val showError: Boolean,
    val error: String?
)

sealed class WalletListState(
    val resultState: WalletResultState,
    val showProgress: Boolean,
    val showEmpty: Boolean,
    val errorState: ErrorState
) : ViewState {

    object Initial : WalletListState(
        resultState = WalletResultState(
            data = emptyList(),
            showResult = false
        ),
        showProgress = false,
        showEmpty = false,
        errorState = ErrorState(
            showError = false,
            error = null
        )
    )

    data class Loading(
        val data: List<WalletUIModel>
    ) : WalletListState(
        resultState = WalletResultState(
            data = data,
            showResult = data.isNotEmpty()
        ),
        showProgress = data.isEmpty(),
        showEmpty = false,
        errorState = ErrorState(
            showError = false,
            error = null
        )
    )

    data class Error(
        val message: String
    ) : WalletListState(
        resultState = WalletResultState(
            data = emptyList(),
            showResult = false
        ),
        showProgress = false,
        showEmpty = false,
        errorState = ErrorState(
            showError = true,
            error = message
        )
    )

    data class Loaded(
        val data: List<WalletUIModel>
    ) : WalletListState(
        resultState = WalletResultState(
            data = data,
            showResult = data.isNotEmpty()
        ),
        showProgress = false,
        showEmpty = data.isEmpty(),
        errorState = ErrorState(
            showError = false,
            error = null
        )
    )

    companion object {
        val Hide: WalletListState
            get() = Initial
    }
}
