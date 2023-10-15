package com.puzzling.puzzlingaos.data.model.request

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RequestLoginDto(
    @SerializedName("socialPlatform")
    val socialPlatform: String,
)
