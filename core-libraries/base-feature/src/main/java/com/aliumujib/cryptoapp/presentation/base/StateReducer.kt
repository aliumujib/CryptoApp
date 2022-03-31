package com.aliumujib.cryptoapp.presentation.base

public interface StateReducer<S : ScreenState, R : ViewResult> {
    public fun reduce(oldState: S, result: R): S
}
