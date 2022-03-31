package com.aliumujib.cryptoapp.walletdata

import com.aliumujib.cryptoapp.coremodels.Wallet
import com.aliumujib.cryptoapp.walletdata.remote.WalletDataSource

class FakeWalletSource(private val wallet: List<Wallet>) : WalletDataSource {

    override fun fetch(): List<Wallet> {
        return wallet
    }
}
