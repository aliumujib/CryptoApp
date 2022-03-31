package com.aliumujib.cryptoapp.dashboard.ui.components.wallet

import android.content.res.Resources
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.aliumujib.cryptoapp.dashboard.databinding.LayoutWalletListBinding
import com.aliumujib.cryptoapp.dashboard.presentation.model.WalletUIModel
import com.aliumujib.cryptoapp.dashboard.presentation.state.WalletListState
import com.aliumujib.cryptoapp.presentation.view.UIComponent
import com.aliumujib.cryptoapp.uicommons.dpToPx
import io.cabriole.decorator.LinearMarginDecoration

class WalletListView(
    resources: Resources,
    private val navigationAction: (WalletUIModel) -> Unit,
    private val view: LayoutWalletListBinding
) : UIComponent<WalletListState>() {

    private val walletAdapter: WalletAdapter by lazy {
        WalletAdapter { model ->
            navigationAction(model)
        }
    }

    init {
        view.walletsRv.addItemDecoration(
            LinearMarginDecoration(
            leftMargin = resources.dpToPx(16),
            topMargin = resources.dpToPx(16),
            rightMargin = resources.dpToPx(16),
            bottomMargin = resources.dpToPx(16),
            orientation = RecyclerView.VERTICAL
        ))
        view.walletsRv.adapter = walletAdapter
    }

    override fun render(state: WalletListState) {
        walletAdapter.submitList(state.resultState.data)
        view.run {
            walletsRv.isVisible = state.resultState.showResult
            progressBar.isVisible = state.showProgress
            emptyState.isVisible = state.showEmpty
            errorState.isVisible = state.errorState.showError
            errorState.text = state.errorState.error
        }
    }
}
