package com.bagibagi.app.data.model

import com.bagibagi.app.data.response.NotifBarangRecipient
import com.bagibagi.app.data.response.NotifBarangRequester
import com.bagibagi.app.data.response.NotifRecipient
import com.bagibagi.app.data.response.NotifRequester

data class NotificationModel(
    val id : Int,
    val requester: NotifRequester,
    val barangRequester: NotifBarangRequester,
    val recipient: NotifRecipient,
    val barangRecipient: NotifBarangRecipient,
    val qtyBarangRequester : Int,
    val qtyBarangRequested : Int,
    val status : String
)