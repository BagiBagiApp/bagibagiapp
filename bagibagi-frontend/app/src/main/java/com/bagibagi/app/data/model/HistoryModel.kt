package com.bagibagi.app.data.model

import com.bagibagi.app.data.response.HistoryBarangRecipient
import com.bagibagi.app.data.response.HistoryBarangRequester
import com.bagibagi.app.data.response.HistoryRecipient
import com.bagibagi.app.data.response.HistoryRequester

data class HistoryModel(
    val id : Int,
    val requester: HistoryRequester,
    val barangRequester: HistoryBarangRequester,
    val recipient: HistoryRecipient,
    val barangRecipient: HistoryBarangRecipient,
    val qtyBarangRequester: Int,
    val qtyBarangRequested: Int,
    val status : String
)