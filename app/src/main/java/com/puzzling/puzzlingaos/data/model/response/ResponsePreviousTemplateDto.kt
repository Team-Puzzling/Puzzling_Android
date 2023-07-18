package com.puzzling.puzzlingaos.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponsePreviousTemplateDto(
    @SerialName("status")
    val status: Int,
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: PreviousTemplateIdData,
) {
    @Serializable
    data class PreviousTemplateIdData(
        @SerialName("previousTemplateId")
        val previousTemplateId: Int,
    )
}
