package com.puzzling.puzzlingaos.data.model.response

import com.puzzling.puzzlingaos.domain.entity.DetailRetro
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDetailRetroDto(
    @SerialName("status")
    val status: Int,
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: Data,
) {
    @Serializable
    data class Data(
        @SerialName("projectName")
        val projectName: String,
        @SerialName("reviews")
        val reviews: List<DetailReviewData>,
    ) {
        @Serializable
        data class DetailReviewData(
            @SerialName("reviewId")
            val reviewId: Int?,
            @SerialName("reviewDay")
            val reviewDay: String,
            @SerialName("reviewDate")
            val reviewDate: String,
            @SerialName("reviewTemplateId")
            val reviewTemplateId: Int?,
            @SerialName("contents")
            val contents: List<Content>?,
        ) {
            @Serializable
            data class Content(
                @SerialName("title")
                val title: String,
                @SerialName("content")
                val content: String,
            )
        }

        fun toDetailRetro() = reviews.map { reviews ->
            DetailRetro(
                reviewId = reviews.reviewId,
                reviewDay = reviews.reviewDay,
                reviewDate = reviews.reviewDate,
                reviewTemplateId = reviews.reviewTemplateId,
                content = reviews.contents?.map { content ->
                    DetailRetro.Content(content.title, content.content)
                },

            )
        }
    }
}
