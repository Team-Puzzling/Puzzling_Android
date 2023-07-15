package com.puzzling.puzzlingaos.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDetailRetroDto(
    @SerialName("reviewId")
    val reviewId: Int?,
    @SerialName("reviewDay")
    val reviewDay: String,
    @SerialName("reviewDate")
    val reviewDate: String,
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
