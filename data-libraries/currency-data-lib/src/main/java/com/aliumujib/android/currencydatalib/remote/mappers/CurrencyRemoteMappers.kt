package com.aliumujib.android.currencydatalib.remote.mappers

import com.aliumujib.android.currencydatalib.remote.model.CurrencyResponse
import com.aliumujib.cryptoapp.cache.currencies.models.CurrencyCacheModel
import com.aliumujib.cryptoapp.cache.mapper.CacheModelMapper
import com.aliumujib.cryptoapp.coremodels.Currency
import com.aliumujib.cryptoapp.remote.mapper.RemoteModelMapper
import javax.inject.Inject

class CurrencyRemoteMappers @Inject constructor() : RemoteModelMapper<Currency, CurrencyResponse> {

    override fun mapToModel(response: CurrencyResponse): Currency {
        return with(response) {
            Currency(
                blockchain_symbol,
                code,
                coin_id,
                colorful_image_url,
                contract_address,
                deposit_address_tag_name,
                deposit_address_tag_type,
                display_decimal,
                explorer,
                gas_limit,
                gray_image_url,
                has_deposit_address_tag,
                is_erc20,
                min_balance,
                name,
                num_confirmation_required,
                supports_legacy_address,
                symbol,
                token_decimal,
                token_decimal_value,
                trading_symbol,
                withdrawal_eta
            )
        }
    }

}