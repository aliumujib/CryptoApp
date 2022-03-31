package com.aliumujib.cryptoapp.currencydatalib.cache.mappers

import com.aliumujib.cryptoapp.cache.currencies.models.CurrencyCacheModel
import com.aliumujib.cryptoapp.cache.mapper.CacheModelMapper
import com.aliumujib.cryptoapp.coremodels.Currency
import javax.inject.Inject

class CurrencyCacheMappers @Inject constructor() : CacheModelMapper<Currency, CurrencyCacheModel> {

    override fun mapToModel(entity: CurrencyCacheModel): Currency {
        return with(entity) {
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

    override fun mapToEntity(model: Currency): CurrencyCacheModel {
        return with(model) {
            CurrencyCacheModel(
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
