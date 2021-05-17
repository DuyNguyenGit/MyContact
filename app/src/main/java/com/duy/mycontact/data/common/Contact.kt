package com.duy.mycontact.data.common

import com.google.gson.annotations.SerializedName

data class Contact(
    @SerializedName("avatar") val avatar: String,
    @SerializedName("email") val email: String,
    @SerializedName("first_name") val first_name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("last_name") val last_name: String
)