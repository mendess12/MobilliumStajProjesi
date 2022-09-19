package com.yusufmendes.basicnoteapp.util.extension

import android.view.View

fun View.enabled() {
    this.isEnabled = true
}

fun View.disabled() {
    this.isEnabled = false
}
