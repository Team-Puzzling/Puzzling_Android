package com.puzzling.puzzlingaos.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestReviewAARDto(
    @SerialName("reviewTemplateId")
    val reviewTemplateId: Int,
    @SerialName("initialGoal")
    val initialGoal: String,
    @SerialName("result")
    val result: String,
    @SerialName("difference")
    val difference: String,
    @SerialName("persistence")
    val persistence: String,
    @SerialName("actionPlan")
    val actionPlan: String,
)
