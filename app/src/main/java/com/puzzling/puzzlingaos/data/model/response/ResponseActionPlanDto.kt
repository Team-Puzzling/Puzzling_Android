package com.puzzling.puzzlingaos.data.model.response

import com.puzzling.puzzlingaos.domain.entity.ActionPlan
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
    val data: List<ActionPlanData>,
) {
    @Serializable
    data class ActionPlanData(
        @SerialName("actionPlanContent")
        val actionPlanContent: String,
        @SerialName("actionPlanDate")
        val actionPlanDate: String,
    )

    fun toActionPlan() = data.map { plan ->
        ActionPlan(
            actionPlanContent = plan.actionPlanContent,
            actionPlanDate = plan.actionPlanDate,
        )
    }
}
