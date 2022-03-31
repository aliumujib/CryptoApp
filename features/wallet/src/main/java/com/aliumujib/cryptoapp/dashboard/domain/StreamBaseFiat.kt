package com.aliumujib.cryptoapp.dashboard.domain

import com.aliumujib.cryptoapp.coredomain.utils.FlowUseCase
import com.aliumujib.cryptoapp.coredomain.utils.PostExecutionThread
import com.aliumujib.cryptoapp.currencydatalib.domain.CurrenciesRepository
import com.aliumujib.cryptoapp.dashboard.presentation.BaseFiatCurrency
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class StreamBaseFiat @Inject constructor(
    private val currencyRepository: CurrenciesRepository,
    postExecutionThread: PostExecutionThread
) : FlowUseCase<Unit, BaseFiatCurrency>(postExecutionThread) {

    override fun build(params: Unit?): Flow<BaseFiatCurrency> {
        return currencyRepository.streamBaseFiatCurrencyCode()
    }
}
