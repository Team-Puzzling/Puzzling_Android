package com.puzzling.puzzlingaos.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamRetrospectList(
    @SerialName("reviewDay")
    val reviewDay: String,
    @SerialName("reviewDate")
    val reviewDate: String,
    @SerialName("reviewWriters")
    val reviewWriters: ArrayList<ReviewWriterList>?,
    @SerialName("nonReviewWriters")
    val nonReviewWriters: ArrayList<NonReviewWriterList>?,
) {
    @Serializable
    data class ReviewWriterList(
        @SerialName("memberNickname")
        val memberNickname: String,
        @SerialName("memberRole")
        val memberRole: String,
    )

    @Serializable
    data class NonReviewWriterList(
        @SerialName("memberNickname")
        val memberNickname: String,
        @SerialName("memberRole")
        val memberRole: String,
    )
}
