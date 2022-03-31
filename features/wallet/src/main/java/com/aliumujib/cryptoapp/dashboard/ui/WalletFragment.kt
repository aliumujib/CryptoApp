package com.aliumujib.cryptoapp.dashboard.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aliumujib.cryptoapp.dashboard.R
import com.aliumujib.cryptoapp.dashboard.databinding.WalletFragmentBinding
import com.aliumujib.cryptoapp.dashboard.navigation.WalletNavigator
import com.aliumujib.cryptoapp.dashboard.presentation.WalletViewModel
import com.aliumujib.cryptoapp.dashboard.presentation.state.WalletScreenState
import com.aliumujib.cryptoapp.dashboard.ui.components.HeaderView
import com.aliumujib.cryptoapp.dashboard.ui.components.wallet.WalletListView
import com.aliumujib.cryptoapp.uicommons.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WalletFragment : Fragment(R.layout.wallet_fragment) {

    private val binding: WalletFragmentBinding by viewBinding(WalletFragmentBinding::bind)

    @Inject
    lateinit var navigator: WalletNavigator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: WalletViewModel by viewModels()

        viewModel.run {
            subscribe(
                component = HeaderView(
                    view = binding.headerView
                ),
                stateTransform = WalletScreenState::walletHeaderState
            )
            subscribe(
                component = WalletListView(
                    view = binding.walletList,
                    resources = resources,
                    navigationAction = {
                        navigator.openWalletDetails(it.coinId)
                    }
                ),
                stateTransform = WalletScreenState::walletListState
            )

            disposeAll(viewLifecycleOwner)
        }
    }
}
