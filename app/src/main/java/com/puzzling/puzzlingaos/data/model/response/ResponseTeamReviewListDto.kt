package com.puzzling.puzzlingaos.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseTeamReviewListDto(
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
        @SerialName("projectName")
        val projectName: String,
        @SerialName("memberReviews")
        val memberReviews: ArrayList<MemberReviews>,
    ) {
        @Serializable
        data class MemberReviews(
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

            fun getReview() = reviewWriters?.map { reviewWriters ->
                ReviewWriters(
                    reviewWriters.memberNickname ?: "null",
                    reviewWriters.memberRole ?: "null",
                )
            }
            fun getReviewArray(): ArrayList<ReviewWriters>? {
                return getReview()?.let { ArrayList(it) }
            }
            fun getNonReview() = nonReviewWriters?.map { nonReviewWriters ->
                NonReviewWriters(
                    nonReviewWriters.memberNickname ?: "null",
                    nonReviewWriters.memberRole ?: "null",
                )
            }
            fun getNonReviewArray(): ArrayList<NonReviewWriters>? {
                return getNonReview()?.let { ArrayList(it) }
            }
        }
        fun toTeamRetro() = memberReviews.map { data ->
            MemberReviews(
                data.reviewDay ?: "null",
                data.reviewDate ?: "null",
                data.getReviewArray() ?: null,
                data.getNonReviewArray() ?: null,

            )
        }
        fun toTeamRetroArray(): ArrayList<MemberReviews>? {
            return toTeamRetro()?.let { ArrayList(it) }
        }
    }
}
