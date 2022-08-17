package com.aliumujib.cryptoapp.currencydatalib.remote.mappers

import com.aliumujib.cryptoapp.coremodels.Currency
import com.aliumujib.cryptoapp.currencydatalib.remote.model.CurrencyResponse
import com.aliumujib.cryptoapp.remote.mapper.RemoteModelMapper
import javax.inject.Inject

class CurrencyRemoteMappers @Inject constructor() : RemoteModelMapper<Currency, CurrencyResponse> {

    override fun mapToModel(response: CurrencyResponse): Currency {
        return with(response) {
            Currency(
                blockchainSymbol,
                code,
                coinId,
                colorfulImageUrl,
                contractAddress,
                depositAddressTagName,
                depositAddressTagType,
                displayDecimal,
                explorer,
                gasLimit,
                grayImageUrl,
                hasDepositAddressTag,
                isErc20,
                minBalance,
                name,
                numConfirmationRequired,
                supportsLegacyAddress,
                symbol,
                tokenDecimal,
                tokenDecimalValue,
                tradingSymbol,
                withdrawalEta
            )
        }
    }
}
