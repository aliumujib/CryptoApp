package com.aliumujib.cryptoapp.dashboard.mapper

import com.aliumujib.cryptoapp.coremodels.Wallet
import com.aliumujib.cryptoapp.coremodels.WalletsWithExchangeRates
import com.aliumujib.cryptoapp.dashboard.presentation.model.WalletUIModel
import com.aliumujib.cryptoapp.presentation.mapper.ModelMapper
import com.aliumujib.cryptoapp.uicommons.orZero
import java.lang.UnsupportedOperationException
import javax.inject.Inject

class WalletModelMapper @Inject constructor() : ModelMapper<WalletUIModel, Wallet> {

    fun mapToModel(domain: WalletsWithExchangeRates): List<WalletUIModel> {
        return domain.wallets.map {
            val exchangeRate = domain.exchangeRates[it.currencyCode].orZero()
            val fiatAmount = it.amount * exchangeRate
            this.mapToModel(it)
                .copy(fiatAmount = fiatAmount, fiatCurrencyCode = domain.baseFiatCurrency)
        }
    }

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
