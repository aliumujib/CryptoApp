package com.aliumujib.cryptoapp.uicommons

import android.content.res.Resources
import android.util.DisplayMetrics
import kotlin.math.roundToInt

/***
 *
 * Copy paste coding made possible by
 * https://stackoverflow.com/questions/8309354/formula-px-to-dp-dp-to-px-android
 *
 */

fun Resources.dpToPx(dp: Int): Int {
    val displayMetrics: DisplayMetrics = displayMetrics
    return (dp * (displayMetrics.density) + 0.5).roundToInt()
}
