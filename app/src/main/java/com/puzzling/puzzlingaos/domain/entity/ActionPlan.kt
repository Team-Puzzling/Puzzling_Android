package com.puzzling.puzzlingaos.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActionPlan(
    @SerialName("actionPlanContent")
    val actionPlanContent: String,
    @SerialName("actionPlanDate")
    val actionPlanDate: String,
)
