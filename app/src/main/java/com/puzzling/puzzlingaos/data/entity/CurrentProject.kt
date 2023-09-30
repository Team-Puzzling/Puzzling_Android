package com.puzzling.puzzlingaos.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class CurrentProject(
    val projectId: Int,
    val projectName: String,
)
