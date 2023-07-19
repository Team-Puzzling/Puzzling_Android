package com.puzzling.puzzlingaos.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestInvitationCode(
    @SerialName("projectId")
    val projectId: Int,
    @SerialName("memberProjectNickname")
    val memberProjectNickname: String,
    @SerialName("memberProjectRole")
    val memberProjectRole: String,
)
