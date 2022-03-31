package com.aliumujib.cryptoapp.presentation.view

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

internal inline fun dispose(
    lifecycleOwner: LifecycleOwner,
    crossinline action: () -> Unit
) {
    object : DefaultLifecycleObserver {
        init {
            lifecycleOwner.lifecycle.removeObserver(this)
            lifecycleOwner.lifecycle.addObserver(this)
        }

        override fun onDestroy(owner: LifecycleOwner) {
            action()
            owner.lifecycle.removeObserver(this)
            super.onDestroy(owner)
        }
    }
}
