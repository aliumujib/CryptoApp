package com.aliumujib.cryptoapp.walletdata.remote.mappers


import com.aliumujib.android.currencydatalib.domain.HighSpeedCurrencyCache
import com.aliumujib.cryptoapp.coremodels.Wallet
import com.aliumujib.cryptoapp.remote.mapper.RemoteModelMapper
import com.aliumujib.cryptoapp.walletdata.remote.model.WalletResponse
import javax.inject.Inject

class WalletRemoteMappers @Inject constructor(private val highSpeedCurrencyCache: HighSpeedCurrencyCache) : RemoteModelMapper<Wallet, WalletResponse> {

    override fun mapToModel(response: WalletResponse): Wallet {
        return with(response) {
            Wallet(
                highSpeedCurrencyCache[response.currency],
                amount
            )
        }
    }

}