package com.aliumujib.cryptoapp.ratedatalib.remote.impl

import android.content.Context
import android.util.Log
import com.aliumujib.cryptoapp.coremodels.ExchangeRate
import com.aliumujib.cryptoapp.ratedatalib.remote.mappers.RateRemoteMappers
import com.aliumujib.cryptoapp.ratedatalib.remote.model.LiveRatesResponse
import com.aliumujib.cryptoapp.remote.datasource.DataSource
import com.aliumujib.cryptoapp.remote.datasource.FileDataSource
import com.aliumujib.cryptoapp.remote.datasource.parseJson
import com.squareup.moshi.Moshi
import javax.inject.Inject

typealias RatesDataSource = DataSource<List<ExchangeRate>>

class RatesDataSourceImpl @Inject constructor(
    private val moshi: Moshi,
    private val rateRemoteMappers: RateRemoteMappers,
    context: Context
) : FileDataSource<List<ExchangeRate>>(context) {

    override fun fetch(): List<ExchangeRate>? {
        val response = parseJson<LiveRatesResponse>(moshi, readFileContents("live-rates-json.md"))
        response?.let {
            if (response.ok) {
                return response.tiers.map {
                    rateRemoteMappers.mapToModel(it)
                }
            } else {
                throw Exception("An error occurred while fetching currencies")
            }
        }
        Log.d("Rates", response.toString())

        return null
    }
}
