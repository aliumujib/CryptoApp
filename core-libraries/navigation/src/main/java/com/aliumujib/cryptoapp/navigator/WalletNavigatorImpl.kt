package com.aliumujib.cryptoapp.navigator

import androidx.core.net.toUri
import androidx.navigation.NavController
import com.aliumujib.cryptoapp.dashboard.navigation.WalletNavigator
import javax.inject.Inject

class WalletNavigatorImpl @Inject constructor(private val navController: NavController) : WalletNavigator {

    override fun openWalletDetails(coinId: String) {
        navController.navigate("crypto://.*/wallet-details?coinId=$coinId".toUri())
    }
}
