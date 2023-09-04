package com.puzzling.puzzlingaos.data.model.request

import com.puzzling.puzzlingaos.domain.entity.JoinProjectInfo
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

fun JoinProjectInfo.toRequestJoinProjectDto() = RequestInvitationCode(
    projectId,
    memberProjectNickname,
    memberProjectRole,
)
