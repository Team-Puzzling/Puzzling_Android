package com.puzzling.puzzlingaos.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseActionPlanDto(
    @SerialName("status")
    val status: Int,
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: List<ActionPlanDto>,
)

@Serializable
data class ActionPlanDto(
    @SerialName("actionPlanContent")
    val actionPlanContent: String,
    @SerialName("actionPlanDate")
    val actionPlanDate: String?,
)
