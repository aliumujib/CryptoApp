package com.aliumujib.cryptoapp.coredomain.repository

import com.aliumujib.cryptoapp.coremodels.Wallet
import kotlinx.coroutines.flow.Flow

interface WalletsRepository {

    suspend fun streamWallets() : Flow<List<Wallet>>

}
