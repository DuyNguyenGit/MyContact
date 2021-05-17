package com.duy.githubclients.util

import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.duy.mycontact.data.common.Contact

@BindingAdapter("app:errorRes")
fun setErrorMessage(view: TextView, resError: Int?): Unit {
    view.setResError(resError)
}

@BindingAdapter("avatar")
fun loadAvatar(view: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(view.context)
            .load(it).apply(RequestOptions().circleCrop())
            .into(view)
    }
}

@BindingAdapter("app:visible")
fun visibitity(view: View, isVisible: Boolean) {
    if (isVisible)
        view.visibility = View.VISIBLE
    else
        view.visibility = View.GONE
}

@BindingAdapter("firstName")
fun firstName(view: EditText, contact: Contact?) {
    Log.d("firstName", "firstName: >>>")
    contact?.let {
        Log.d("firstName", "firstName: >>${contact.first_name}")
        view.setText(contact.first_name)
    }
}