package com.puzzling.puzzlingaos.data.model.response

import com.puzzling.puzzlingaos.domain.entity.ReviewType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseReviewTypeDto(
    @SerialName("status")
    val status: Int,
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: List<ReviewTypeData>,
) {
    @Serializable
    data class ReviewTypeData(
        @SerialName("reviewTemplateId")
        val reviewTemplateId: Int,
        @SerialName("reviewTemplateName")
        val reviewTemplateName: String,
        @SerialName("reviewTemplateMeaning")
        val reviewTemplateMeaning: String,
    )

    fun toReviewType() = data.map { type ->
        ReviewType(
            reviewTemplateId = type.reviewTemplateId,
            reviewTemplateName = type.reviewTemplateName,
            reviewTemplateMeaning = type.reviewTemplateMeaning,
        )
    }
}
