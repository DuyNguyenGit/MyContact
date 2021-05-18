package com.duy.githubclients.util

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.widget.SearchView

fun TextView.setResError(resError: Int?) {
    resError?.let {
        error = "Has Error! Please check connection and retry"
    } ?: setError(null)
}

fun Activity.hideKeyboard() = currentFocus?.let {
    val imm = it.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(it.windowToken, 0)
}
