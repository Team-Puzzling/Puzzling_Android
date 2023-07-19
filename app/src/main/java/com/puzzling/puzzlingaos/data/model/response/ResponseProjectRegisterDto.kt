package com.puzzling.puzzlingaos.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseProjectRegisterDto(
    @SerialName("status")
    val status: Int,
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: ProjectCode,
) {
    @Serializable
    data class ProjectCode(
        @SerialName("projectCode")
        val projectCode: String,
        @SerialName("projectId")
        val projectId: Int
    )

    fun getProjectCode(): ProjectCode {
        return ProjectCode(
            projectCode = data.projectCode,
            projectId = data.projectId
        )
    }
}
