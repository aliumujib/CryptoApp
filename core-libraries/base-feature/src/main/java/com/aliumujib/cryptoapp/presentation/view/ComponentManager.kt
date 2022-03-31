package com.aliumujib.cryptoapp.presentation.view

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.aliumujib.cryptoapp.presentation.base.BaseComponentManager
import com.aliumujib.cryptoapp.presentation.base.NoOpTransform
import com.aliumujib.cryptoapp.presentation.base.ScreenState
import com.aliumujib.cryptoapp.presentation.base.StateTransform
import com.aliumujib.cryptoapp.presentation.base.Subscriber
import com.aliumujib.cryptoapp.presentation.base.ViewResult
import com.aliumujib.cryptoapp.presentation.base.ViewState
import com.aliumujib.cryptoapp.presentation.statemachine.StateMachine


@MainThread
abstract class ComponentManager<S : ScreenState, out R : ViewResult>(
    private val stateMachine: StateMachine<S, R>
) : ViewModel(), BaseComponentManager<S> {

    override fun <VS : ViewState> subscribe(
        component: Subscriber<VS>,
        stateTransform: StateTransform<S, VS>
    ) {
        stateMachine.subscribe(component, stateTransform)
    }

    override fun <VS : ViewState> subscribe(component: Subscriber<VS>) {
        stateMachine.subscribe(component, NoOpTransform())
    }

    fun disposeAll(owner: LifecycleOwner) {
        dispose(
            lifecycleOwner = owner,
            action = stateMachine::unSubscribeComponents
        )
    }

    override fun onCleared() {
        stateMachine.unSubscribe()
    }
}
