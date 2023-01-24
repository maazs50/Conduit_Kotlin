package io.realworld.condiut.extensions

import android.annotation.SuppressLint
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("ConstantLocale")
val isoDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",Locale.getDefault())
@SuppressLint("ConstantLocale")
val appDateFormat = SimpleDateFormat("MMMM dd, yyyy",Locale.getDefault())

fun TextView.showFormattedDate(timeStamp: String){
    val date = isoDateFormat.parse(timeStamp)
    text = appDateFormat.format(date)
}