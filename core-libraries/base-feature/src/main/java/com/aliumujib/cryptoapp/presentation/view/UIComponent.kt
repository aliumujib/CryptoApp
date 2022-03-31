package com.aliumujib.cryptoapp.presentation.view

import androidx.annotation.UiThread
import com.aliumujib.cryptoapp.presentation.base.DispatchIntent
import com.aliumujib.cryptoapp.presentation.base.Subscriber
import com.aliumujib.cryptoapp.presentation.base.ViewIntent
import com.aliumujib.cryptoapp.presentation.base.ViewState


/**
 * Represents a basic UI component that can be part of a screen
 */
abstract class UIComponent<ComponentState : ViewState> : Subscriber<ComponentState> {

    @UiThread
    protected abstract fun render(state: ComponentState)

    @UiThread
    protected fun sendIntent(intent: ViewIntent) {
        dispatchIntent.invoke(intent)
    }

    override var dispatchIntent: DispatchIntent = NoOpIntentDispatcher

    override fun onNewState(state: ComponentState) {
        render(state)
    }
}

private val NoOpIntentDispatcher: DispatchIntent
    get() = {}

abstract class StatelessUIComponent : UIComponent<ViewState>() {
    override fun render(state: ViewState) {}
}
