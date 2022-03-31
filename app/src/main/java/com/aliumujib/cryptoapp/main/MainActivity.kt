package com.aliumujib.cryptoapp.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.aliumujib.cryptoapp.currencydatalib.domain.CurrenciesRepository
import com.aliumujib.cryptoapp.databinding.ActivityMainBinding
import com.aliumujib.cryptoapp.ratedatalib.domain.RatesRepository
import com.aliumujib.cryptoapp.walletdata.domain.WalletsRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var currenciesRepository: CurrenciesRepository

    @Inject
    lateinit var walletsRepository: WalletsRepository

    @Inject
    lateinit var exchangeRateRepository: RatesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        currenciesRepository.streamCurrencies()
//            .flatMapConcat {
//                Timber.d("Currencies: $it")
//                walletsRepository.streamWallets()
//            }.onEach {
//
//                Timber.d("Wallets: $it")
//            }.catch {
//                it.printStackTrace()
//            }
//            .flowOn(Dispatchers.IO)
//            .launchIn(lifecycleScope)

    }
}