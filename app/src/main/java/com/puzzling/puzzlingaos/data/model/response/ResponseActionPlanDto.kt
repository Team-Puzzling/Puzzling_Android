package com.puzzling.puzzlingaos.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseActionPlanDto(
    val status: Int,
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: ActionPlanData,
) {
    @Serializable
    data class ActionPlanData(
        @SerialName("actionPlanContent")
        val actionPlanContent: String,
        @SerialName("actionPlanDate")
        val actionPlanDate: String,
    )
}
