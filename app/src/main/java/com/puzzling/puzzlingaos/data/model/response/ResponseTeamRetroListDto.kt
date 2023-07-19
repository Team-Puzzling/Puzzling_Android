package com.puzzling.puzzlingaos.data.model.response

import com.puzzling.puzzlingaos.domain.entity.TeamRetrospectList
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
//
//        fun toPuzzleBoard() = userPuzzleBoard.map { board ->
//            MyPuzzleBoard(
//                board.reviewDate ?: "null",
//                board.reviewId ?: -1,
//                board.puzzleAssetName,
//            )
//        }

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
    fun toTeamRetro() = data.map { data ->
        Data(
            data.reviewDay ?: "null",
            data.reviewDate ?: "null",
            data.getReviewArray() ?: null,
            data.getNonReviewArray() ?: null,

        )
    }
    fun toTeamRetroArray(): ArrayList<Data>? {
        return toTeamRetro()?.let { ArrayList(it) }
    }
}
