package com.puzzling.puzzlingaos.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestProjectRegisterDto(
    @SerialName("projectName")
    val projectName: String,
    @SerialName("projectIntro")
    val projectIntro: String,
    @SerialName("projectStartDate")
    val projectStartDate: String,
    @SerialName("memberProjectRole")
    val memberProjectRole: String,
    @SerialName("memberProjectNickname")
    val memberProjectNickname: String,
    @SerialName("reviewCycle")
    val reviewCycle: ArrayList<String>,
)
