package com.duy.mycontact.data.contact_list

import com.duy.mycontact.data.common.Contact
import com.duy.mycontact.data.common.Support
import com.google.gson.annotations.SerializedName

data class ContactListResponse(
    @SerializedName("data") val contacts: List<Contact>,
    @SerializedName("page") val page: Int,
    @SerializedName("per_page") val per_page: Int,
    @SerializedName("support") val support: Support,
    @SerializedName("total") val total: Long,
    @SerializedName("total_pages") val total_pages: Int
)