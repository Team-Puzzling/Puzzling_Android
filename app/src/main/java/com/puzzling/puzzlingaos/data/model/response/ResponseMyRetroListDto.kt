package com.puzzling.puzzlingaos.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseMyRetroListDto(
    @SerialName("reviewId")
    val reviewId: Int,
    @SerialName("reviewDate")
    val reviewDate: String,
    @SerialName("contents")
    val contents: String,
)
