package com.puzzling.puzzlingaos.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseTeamRetroListDto(
    @SerialName("status")
    val status: Int,
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: ArrayList<Data>,
) {
    @Serializable
    data class Data(
        @SerialName("reviewDay")
        val reviewDay: String,
        @SerialName("reviewDate")
        val reviewDate: String,
        @SerialName("reviewWriters")
        val reviewWriters: ArrayList<ReviewWriters>?,
        @SerialName("nonReviewWriters")
        val nonReviewWriters: ArrayList<NonReviewWriters>?,
    ) {
        @Serializable
        data class ReviewWriters(
            @SerialName("memberNickname")
            val memberNickname: String,
            @SerialName("memberRole")
            val memberRole: String,
        )

        @Serializable
        data class NonReviewWriters(
            @SerialName("memberNickname")
            val memberNickname: String,
            @SerialName("memberRole")
            val memberRole: String,
        )
    }
}
