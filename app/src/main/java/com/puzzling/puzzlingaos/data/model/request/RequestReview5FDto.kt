package com.puzzling.puzzlingaos.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestReview5FDto(
    @SerialName("reviewTemplateId")
    val reviewTemplateId: Int,
    @SerialName("fact")
    val fact: String,
    @SerialName("feeling")
    val feeling: String,
    @SerialName("finding")
    val finding: String,
    @SerialName("feedback")
    val feedback: String,
    @SerialName("actionPlan")
    val actionPlan: String,
)
