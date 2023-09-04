package com.puzzling.puzzlingaos.data.model.response

import com.puzzling.puzzlingaos.domain.entity.InvitationCode
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseInvitationCodeDto(
    @SerialName("status")
    val status: Int,
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: InvitationCodeData,
) {
    @Serializable
    data class InvitationCodeData(
        @SerialName("projectId")
        val projectId: Int,
        @SerialName("projectName")
        val projectName: String,
    ) {
        fun toInvitationCode() = InvitationCode(projectId, projectName)
    }
}
