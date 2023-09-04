package com.puzzling.puzzlingaos.data.model.response

import com.puzzling.puzzlingaos.domain.entity.ProjectReview
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseMyRetroListDto(
    @SerialName("status")
    val status: Int,
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: List<ReviewData>,
) {
    @Serializable
    data class ReviewData(
        @SerialName("reviewId")
        val reviewId: Int,
        @SerialName("reviewDate")
        val reviewDate: String,
        @SerialName("contents")
        val contents: String,
    )

    fun toProjectReview() = data.map { reviewData ->
        ProjectReview(
            reviewData.reviewId,
            reviewData.reviewDate,
            reviewData.contents,
        )
    }
}
