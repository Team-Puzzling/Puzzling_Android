package com.puzzling.puzzlingaos.data.model.response

import com.puzzling.puzzlingaos.domain.entity.Project
import kotlinx.serialization.SerialName

data class ResponseProceedingProjectDto(
    @SerialName("projectId")
    val projectId: Int,
    @SerialName("projectStartDate")
    val projectStartDate: String,
    @SerialName("projectName")
    val projectName: String,
) {
    fun toProject() = Project(
        projectId = projectId,
        projectStartDate = projectStartDate,
        projectName = projectName,

    )
}
