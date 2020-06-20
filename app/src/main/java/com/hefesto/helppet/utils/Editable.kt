package com.hefesto.helppet.utils

import android.text.Editable

val Editable?.isFilled: Boolean get() = !(isNullOrEmpty()||isNullOrBlank())