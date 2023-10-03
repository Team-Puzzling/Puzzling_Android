package com.puzzling.puzzlingaos.data.model.response

import com.puzzling.puzzlingaos.data.entity.Token
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseLoginDto(
    @SerialName("status")
    val status: Int,
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: LoginData,

) {
    @Serializable
    data class LoginData(
        @SerialName("name")
        val name: String,
        @SerialName("memberId")
        val memberId: Int,
        @SerialName("projectId")
        val projectId: Int?,
        @SerialName("projectName")
        val projectName: String?,
        @SerialName("accessToken")
        val accessToken: String,
        @SerialName("refreshToken")
        val refreshToken: String,
        @SerialName("isNewUser")
        val isNewUser: Boolean,
    )

    fun getToken() = Token(data?.accessToken, data?.refreshToken)
}
