package com.puzzling.puzzlingaos.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseActionPlanDto(
    @SerialName("actionPlanContent")
    val actionPlanContent: String,
    @SerialName("actionPlanDate")
    val actionPlanDate: String,
)
