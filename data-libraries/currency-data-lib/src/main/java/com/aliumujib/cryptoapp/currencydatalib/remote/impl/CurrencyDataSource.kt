package com.aliumujib.cryptoapp.currencydatalib.remote.impl

import android.content.Context
import com.aliumujib.cryptoapp.coremodels.Currency
import com.aliumujib.cryptoapp.currencydatalib.remote.mappers.CurrencyRemoteMappers
import com.aliumujib.cryptoapp.currencydatalib.remote.model.CurrencyListResponse
import com.aliumujib.cryptoapp.remote.datasource.DataSource
import com.aliumujib.cryptoapp.remote.datasource.FileDataSource
import com.aliumujib.cryptoapp.remote.datasource.parseJson
import com.squareup.moshi.Moshi
import javax.inject.Inject

typealias CurrencyDataSource = DataSource<List<Currency>>

class CurrencyDataSourceImpl @Inject constructor(
    private val moshi: Moshi,
    private val currencyRemoteMappers: CurrencyRemoteMappers,
    context: Context
) : FileDataSource<List<Currency>>(context)  {

    override fun fetch(): List<Currency>? {
        val response = parseJson<CurrencyListResponse>(moshi, readFileContents("currencies-json.md"))
        response?.let {
            if (response.ok) {
                return response.currencies.map {
                    currencyRemoteMappers.mapToModel(it)
                }
            } else {
                throw Exception("An error occurred while fetching currencies")
            }
        }

        return null
    }
}