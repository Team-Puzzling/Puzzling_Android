package com.puzzling.puzzlingaos.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestReviewTILDto(
    @SerialName("reviewTemplateId")
    val reviewTemplateId: Int,
    @SerialName("liked")
    val liked: String,
    @SerialName("lacked")
    val lacked: String,
    @SerialName("actionPlan")
    val actionPlan: String,
)
