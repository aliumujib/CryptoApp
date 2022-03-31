package com.aliumujib.cryptoapp.walletdetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.aliumujib.cryptoapp.uicommons.viewBinding
import com.aliumujib.cryptoapp.walletdetails.databinding.FragmentWalletDetailsBinding

class WalletDetailsFragment : Fragment(R.layout.fragment_wallet_details) {

    private val binding: FragmentWalletDetailsBinding by viewBinding(FragmentWalletDetailsBinding::bind)

    private val navArgs: WalletDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.walletDetails.text = navArgs.coinId
    }

}