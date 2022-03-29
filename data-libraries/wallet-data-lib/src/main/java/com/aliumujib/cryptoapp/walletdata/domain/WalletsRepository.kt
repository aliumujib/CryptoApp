package com.aliumujib.cryptoapp.walletdata.domain

import com.aliumujib.cryptoapp.coremodels.Wallet
import kotlinx.coroutines.flow.Flow

interface WalletsRepository {

    fun streamWallets(): Flow<List<Wallet>>

}