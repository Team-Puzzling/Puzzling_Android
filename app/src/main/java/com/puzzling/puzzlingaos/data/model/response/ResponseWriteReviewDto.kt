package com.puzzling.puzzlingaos.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseWriteReviewDto(
    @SerialName("status")
    val status: Int,
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: List<WriteReviewData>,
) {
    @Serializable
    data class WriteReviewData(
        @SerialName("reviewTemplateId")
        val reviewTemplateId: Int,
        @SerialName("reviewTemplateName")
        val reviewTemplateName: String,
        @SerialName("reviewTempalteMeaning")
        val reviewTempalteMeaning: Int,
    )
}
