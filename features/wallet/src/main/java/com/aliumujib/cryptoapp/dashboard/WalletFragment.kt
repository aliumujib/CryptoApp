package com.aliumujib.cryptoapp.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.aliumujib.android.uicommons.viewBinding
import com.aliumujib.cryptoapp.dashboard.databinding.WalletFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WalletFragment : Fragment() {

    private val binding: WalletFragmentBinding by viewBinding(WalletFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}