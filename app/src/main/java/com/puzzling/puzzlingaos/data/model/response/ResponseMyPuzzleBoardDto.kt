package com.puzzling.puzzlingaos.data.model.response

import com.puzzling.puzzlingaos.domain.entity.Puzzle
import com.puzzling.puzzlingaos.domain.entity.UserPuzzleBoard
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseMyPuzzleBoardDto(
    @SerialName("status")
    val status: Int,
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: MyPuzzleBoardData,
) {
    @Serializable
    data class MyPuzzleBoardData(
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

        )

        fun toPuzzle(): Puzzle {
            return Puzzle(
                nickname = myPuzzle.nickname,
                puzzleCount = myPuzzle.puzzleCount,
            )
        }

        @Serializable
        data class UserPuzzleBoard(
            @SerialName("reviewDate")
            val reviewDate: String,
            @SerialName("reviewId")
            val reviewId: Int,
            @SerialName("puzzleAssetName")
            val puzzleAssetName: String,
        )

        fun toUserPuzzleBoardList(): List<UserPuzzleBoard> {
            return userPuzzleBoard
        }
    }
}
