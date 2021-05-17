package com.duy.mycontact.data.contact_list

import com.google.gson.annotations.SerializedName

data class ContactListResponse(
    @SerializedName("data") val contacts: List<Contact>,
    @SerializedName("page") val page: Int,
    @SerializedName("per_page") val per_page: Int,
    @SerializedName("support") val support: Support,
    @SerializedName("total") val total: Int,
    @SerializedName("total_pages") val total_pages: Int
)