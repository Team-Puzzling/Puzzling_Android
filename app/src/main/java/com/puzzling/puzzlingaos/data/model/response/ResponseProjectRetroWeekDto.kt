package com.puzzling.puzzlingaos.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseProjectRetroWeekDto(
    @SerialName("status")
    val status: Int,
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: ProjectCycle?,
) {
    @Serializable
    data class ProjectCycle(
        @SerialName("projectName")
        val projectName: String,
        @SerialName("projectReviewCycle")
        val projectReviewCycle: String,
    )
}
