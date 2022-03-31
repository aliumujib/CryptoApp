package com.aliumujib.cryptoapp.dashboard.domain

import com.aliumujib.cryptoapp.coredomain.utils.FlowUseCase
import com.aliumujib.cryptoapp.coredomain.utils.PostExecutionThread
import com.aliumujib.cryptoapp.coremodels.Wallet
import com.aliumujib.cryptoapp.currencydatalib.domain.CurrenciesRepository
import com.aliumujib.cryptoapp.walletdata.domain.WalletsRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat

class StreamWallets @Inject constructor(
    private val walletsRepository: WalletsRepository,
    private val currenciesRepository: CurrenciesRepository,
    postExecutionThread: PostExecutionThread
) : FlowUseCase<Unit, List<Wallet>>(postExecutionThread) {

    override fun build(params: Unit?): Flow<List<Wallet>> {
        return currenciesRepository.streamCurrencies().flatMapConcat {
            walletsRepository.streamWallets()
        }
    }
}
