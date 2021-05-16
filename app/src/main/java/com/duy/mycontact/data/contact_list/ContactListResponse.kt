package com.duy.mycontact.data.contact_list

import com.google.gson.annotations.SerializedName

data class ContactListResponse(
    @SerializedName("data") val contacts: List<Contact>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)