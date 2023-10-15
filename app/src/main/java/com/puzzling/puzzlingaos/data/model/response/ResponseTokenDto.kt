package com.puzzling.puzzlingaos.data.model.response

import com.puzzling.puzzlingaos.data.entity.Token
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseTokenDto(
    @SerialName("status")
    val status: Int,
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("date")
    val data: NewToken?,
) {
    @Serializable
    data class NewToken(
        @SerialName("accessToken")
        val accessToken: String,

    )

    fun getToken() = Token(data?.accessToken)
}
