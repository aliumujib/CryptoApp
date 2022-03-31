package com.aliumujib.cryptoapp.dashboard.mapper

import com.aliumujib.cryptoapp.coremodels.Wallet
import com.aliumujib.cryptoapp.dashboard.presentation.model.WalletUIModel
import com.aliumujib.cryptoapp.presentation.mapper.ModelMapper
import java.lang.UnsupportedOperationException
import javax.inject.Inject

class WalletModelMapper @Inject constructor() : ModelMapper<WalletUIModel, Wallet> {
    override fun mapToModel(domain: Wallet): WalletUIModel {
        return with(domain) {
            WalletUIModel(
                currencyCode,
                currency,
                amount,
                0.0,
                null
            )
        }
    }

    override fun mapToDomain(model: WalletUIModel): Wallet {
        throw UnsupportedOperationException()
    }
}
