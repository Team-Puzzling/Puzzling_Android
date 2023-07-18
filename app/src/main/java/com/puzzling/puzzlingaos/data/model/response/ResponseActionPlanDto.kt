package com.puzzling.puzzlingaos.data.model.response

import com.puzzling.puzzlingaos.domain.entity.ActionPlan
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// @Serializable
// data class ResponseActionPlanDto(
//    @SerialName("actionPlanContent")
//    val actionPlanContent: String,
//    @SerialName("actionPlanDate")
//    val actionPlanDate: String,
// ) {
//    fun toActionPlan() = ActionPlan(
//        actionPlanContent = actionPlanContent,
//        actionPlanDate = actionPlanDate,
//    )
//
//    fun mapActionplansToplanItems(): List<ActionPlan> {
//        val photoDetails = mutableListOf<ActionPlan>()
//        photos.orEmpty().forEach { photo ->
//            val id = photo.id ?: return photoDetails
//            val studio = photo.studio ?: return photoDetails
//            val takenAt = photo.takenAt ?: return photoDetails
//            val imageUrl = photo.imageUrl ?: return photoDetails
//            val width = photo.width ?: return photoDetails
//            val height = photo.height ?: return photoDetails
//            val orientationType = when {
//                (width >= height) -> OrientType.HORIZONTAL
//                else -> OrientType.VERTICAL
//            }
//            photoDetails.add(
//                PhotoDetail(id, studio, takenAt, imageUrl, width, height, orientationType)
//            )
//        }
//        return photoDetails
//    }
// }
@Serializable
data class ResponseActionPlanDto(
    @SerialName("actionPlanContent") val actionPlanConntent: List<ActionPlanData>,
) {
    @Serializable
    data class ActionPlanData(
        @SerialName("actionPlanContent")
        val actionPlanContent: String,
        @SerialName("actionPlanDate")
        val actionPlanDate: String,
    )

    fun mapActionplans(): List<ActionPlan> {
        val actionPlans = mutableListOf<ActionPlan>()
        actionPlanConntent.forEach {
            val actionPlanContent = it.actionPlanContent
            val actionPlanDate = it.actionPlanDate
            actionPlans.add(
                ActionPlan(actionPlanContent, actionPlanDate),
            )
        }

        return actionPlans
    }
}
