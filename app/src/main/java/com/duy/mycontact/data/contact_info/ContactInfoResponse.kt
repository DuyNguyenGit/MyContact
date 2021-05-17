package com.duy.mycontact.data.contact_info

import com.duy.mycontact.data.common.Contact
import com.duy.mycontact.data.common.Support
import com.google.gson.annotations.SerializedName

data class ContactInfoResponse(
    @SerializedName("data") val contactInfo: Contact,
    @SerializedName("support") val support: Support
)