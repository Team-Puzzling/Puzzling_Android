package com.puzzling.puzzlingaos.data.model.response

import com.puzzling.puzzlingaos.domain.entity.Project
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseProceedingProjectDto(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: List<ProceedingProjectData>,
) {
    @Serializable
    data class ProceedingProjectData(
        @SerialName("projectId")
        val projectId: Int,
        @SerialName("projectStartDate")
        val projectStartDate: String,
        @SerialName("projectName")
        val projectName: String,
    )

    fun toProject() = data.map { project ->
        Project(
            projectId = project.projectId,
            projectStartDate = project.projectStartDate,
            projectName = project.projectName,

        )
    }
}
