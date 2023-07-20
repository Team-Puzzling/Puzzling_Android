package com.puzzling.puzzlingaos.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseSaveReviewDto(
    @SerialName("status")
    val status: Int,
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: ProjectData,
) {
    @Serializable
    data class ProjectData(
        @SerialName("projectName")
        val projectName: String,
    )
}
