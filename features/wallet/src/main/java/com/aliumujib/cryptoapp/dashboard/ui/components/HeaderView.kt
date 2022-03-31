package com.aliumujib.cryptoapp.dashboard.ui.components

import androidx.core.view.isVisible
import com.aliumujib.cryptoapp.presentation.view.UIComponent
import com.aliumujib.cryptoapp.dashboard.databinding.LayoutWalletHeaderBinding
import com.aliumujib.cryptoapp.dashboard.presentation.state.HeaderViewState
import com.aliumujib.cryptoapp.uicommons.formatToCurrencyString

class HeaderView(private val view: LayoutWalletHeaderBinding) :
    UIComponent<HeaderViewState>() {

    override fun render(state: HeaderViewState) {
        view.run {
            state.result.baseFiatCurrency?.let {
                fiatBalance.text = state.result.totalBalance.formatToCurrencyString(it)
            }
            emptyState.isVisible = state.showEmpty
            errorState.isVisible = state.error.showError
            errorState.text = state.error.error
            progressBar.isVisible = state.showProgress
        }
    }

}