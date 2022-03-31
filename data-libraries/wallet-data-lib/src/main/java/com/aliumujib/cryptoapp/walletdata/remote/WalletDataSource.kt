package com.aliumujib.cryptoapp.walletdata.remote

import android.content.Context
import com.aliumujib.cryptoapp.coremodels.Wallet
import com.aliumujib.cryptoapp.remote.datasource.DataSource
import com.aliumujib.cryptoapp.remote.datasource.FileDataSource
import com.aliumujib.cryptoapp.remote.datasource.parseJson
import com.aliumujib.cryptoapp.walletdata.remote.mappers.WalletRemoteMappers
import com.aliumujib.cryptoapp.walletdata.remote.model.WalletListResponse
import com.squareup.moshi.Moshi
import javax.inject.Inject

typealias WalletDataSource = DataSource<List<Wallet>>

class WalletDataSourceImpl @Inject constructor(
    private val moshi: Moshi,
    private val remoteMappers: WalletRemoteMappers,
    context: Context
) : FileDataSource<List<Wallet>>(context) {

    override fun fetch(): List<Wallet>? {
        val response =
            parseJson<WalletListResponse>(moshi, readFileContents("wallet-balance-json.md"))
        return response?.let {
            if (response.ok) {
                response.wallet?.map {
                    remoteMappers.mapToModel(it)
                }
            } else {
                throw Exception(response.warning)
            }
        }
    }
}