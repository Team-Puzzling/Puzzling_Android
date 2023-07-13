package com.puzzling.puzzlingaos.data.model.response // ktlint-disable filename

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseMyPageProjectDto(
    @SerialName("projectName")
    val projectName: String,
    @SerialName("startDate")
    val startDate: String,
    @SerialName("projectId")
    val projectId: Int,
)
