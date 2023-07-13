package com.puzzling.puzzlingaos.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class ResponseProceedingProjectDto(
    @SerialName("status")
    val status: Int,
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: ProceedingProjecData,
) {
    @Serializable
    data class ProceedingProjecData(
        @SerialName("projectId")
        val projectId: Int,
        @SerialName("projectStartDate")
        val projectStartDate: String,
        @SerialName("projectName")
        val projectName: String,
    )
}
