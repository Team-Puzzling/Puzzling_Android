package com.puzzling.puzzlingaos.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseMyPuzzleBoardDto(
    @SerialName("myPuzzle")
    val myPuzzle: MyPuzzle,
    @SerialName("userPuzzleBoard")
    val userPuzzleBoard: List<UserPuzzleBoard>,
    @SerialName("isReviewDay")
    val isReviewDay: Boolean,
    @SerialName("hasTodayReview")
    val hasTodayReview: Boolean,
) {
    @Serializable
    data class MyPuzzle(
        @SerialName("nickname")
        val nickname: String,
        @SerialName("puzzleCount")
        val puzzleCount: Int,

    ) {
        fun toMyPuzzleEntity() = MyPuzzle(
            nickname,
            puzzleCount,
        )
    }

    @Serializable
    data class UserPuzzleBoard(
        @SerialName("nickname")
        val nickname: String,
        @SerialName("reviewId")
        val reviewId: Int,
        @SerialName("puzzleAssetName")
        val puzzleAssetName: String,
    )
}
