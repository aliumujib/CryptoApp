package com.aliumujib.cryptoapp.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aliumujib.cryptoapp.currencydatalib.domain.CurrenciesRepository
import com.aliumujib.cryptoapp.databinding.ActivityMainBinding
import com.aliumujib.cryptoapp.ratedatalib.domain.RatesRepository
import com.aliumujib.cryptoapp.walletdata.domain.WalletsRepository
import dagger.hilt.android.AndroidEntryPoint
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
