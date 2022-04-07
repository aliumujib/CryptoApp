package com.aliumujib.cryptoapp.dashboard.presentation

import com.aliumujib.cryptoapp.dashboard.mapper.WalletModelMapper
import com.aliumujib.cryptoapp.dashboard.presentation.state.HeaderViewState
import com.aliumujib.cryptoapp.dashboard.presentation.state.WalletListState
import com.aliumujib.cryptoapp.dashboard.presentation.state.WalletScreenState
import com.google.common.truth.Truth.assertThat
import java.lang.IllegalStateException
import konveyor.base.randomBuild
import org.junit.Test

class WalletScreenStateReducerTest {

    private val walletModelMapper = WalletModelMapper()
    private val sut = WalletScreenStateReducer(walletModelMapper)

    @Test
    fun assert_that_reduce_returns_Loaded_when_result_is_LoadedWalletsResult() {
        // GIVEN
        val oldState = WalletScreenState.Initial
        val result = WalletScreenResult.LoadWalletsResult.LoadedWalletsResult(randomBuild())

        // WHEN
        val actual = sut.reduce(oldState, result)

        // THEN
        assertThat(actual).isInstanceOf(WalletScreenState.Loaded::class.java)
    }

    @Test
    fun assert_that_reduce_returns_Error_when_result_is_LoadingError() {
        // GIVEN
        val oldState = WalletScreenState.Initial
        val result = WalletScreenResult.LoadWalletsResult.LoadingError(IllegalStateException("An error occurred"))
        val expected = WalletScreenState.Error(HeaderViewState.Error(result.throwable.message!!), WalletListState.Error(result.throwable.message!!))

        // WHEN
        val actual = sut.reduce(oldState, result)

        // THEN
        assertThat(actual).isEqualTo(expected)
    }
}
