package com.aliumujib.cryptoapp.uicommons

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children

fun ViewGroup.inflate(layout: Int): View {
    val layoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    return layoutInflater.inflate(layout, this, false)
}

fun ViewGroup.recursivelyApplyToChildren(
    ignore: List<Int> = emptyList(),
    function: (child: View) -> Unit
) {
    this.children
        .filter {
            ignore.contains(it.id).not()
        }
        .forEach {
            function.invoke(it)
            if (it is ViewGroup) {
                it.recursivelyApplyToChildren(ignore, function)
            }
        }
}
