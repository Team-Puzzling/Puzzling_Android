package com.puzzling.puzzlingaos.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class ExampleRequest(
    val id: Int,
    val title: String,
    val content: String
)
