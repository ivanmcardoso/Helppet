package com.hefesto.helppet.utils

import android.app.Activity
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.shortToast(@StringRes resId: Int){
Toast.makeText(context, resId, Toast.LENGTH_SHORT).show()
}
fun Fragment.shortToast(text: String){
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}
fun Activity.shortToast(@StringRes resId: Int){
Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()
}
fun Activity.shortToast(text: String){
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}