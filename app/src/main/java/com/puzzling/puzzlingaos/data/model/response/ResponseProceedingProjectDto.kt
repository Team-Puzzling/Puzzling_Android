package com.puzzling.puzzlingaos.data.model.response

import kotlinx.serialization.SerialName

data class ResponseProceedingProjectDto(
    @SerialName("projectId")
    val projectId: Int,
    @SerialName("projectStartDate")
    val projectStartDate: String,
    @SerialName("projectName")
    val projectName: String,
)
