package com.example.tikiapp.utils

import android.content.Context
import android.util.DisplayMetrics
import com.example.tikiapp.utils.Constant.DEFAULT_NUMBER_FORMAT
import com.example.tikiapp.utils.Constant.LOCALE_VN
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

fun Context.dpToPx(dp: Float): Float {
    return dp * getPixelScaleFactor(this)
}

private fun getPixelScaleFactor(context: Context): Float {
    val displayMetrics = context.resources.displayMetrics
    return displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT
}

fun Number.toTextFormat(
    stringFormat: String,
    numberFormat: String = DEFAULT_NUMBER_FORMAT,
    locale: Locale = LOCALE_VN
): String {
    val df = DecimalFormat(numberFormat)
    val symbolFormat = DecimalFormatSymbols.getInstance(locale).apply {
        decimalSeparator = ','
        groupingSeparator = '.'
    }
    df.decimalFormatSymbols = symbolFormat
    val str = df.format(this)
    return String.format(locale, stringFormat, str)
}

