package com.yusufmendes.basicnoteapp.util.extension

import android.util.Patterns
import android.view.View

fun String?.isEmailValid(): Boolean =
    !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String?.isPasswordValid(minPasswordLength: Int): Boolean = this!!.length > minPasswordLength

fun View.changeButtonState(state: Boolean) =
    if (state) this.enabled() else this.disabled()

