package com.duy.mycontact.data.contact_info

import com.google.gson.annotations.SerializedName

data class ContactUpdateData(@SerializedName("name") val name: String,
                             @SerializedName("email") val email: String)
