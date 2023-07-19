package com.puzzling.puzzlingaos.data.model.response

import com.puzzling.puzzlingaos.domain.entity.TeamPuzzleBoard
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseTeamPuzzleBoardDto(
    @SerialName("status")
    val status: Int,
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: TeamPuzzleBoardData,
) {
    @Serializable
    data class TeamPuzzleBoardData(
        @SerialName("myPuzzle")
        val myPuzzle: MyPuzzle,
        @SerialName("teamPuzzleBoard")
        val teamPuzzleBoard: List<UserPuzzleBoard>,
        @SerialName("teamPuzzleBoardCount")
        val teamPuzzleBoardCount: Int,
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

        @Serializable
        data class UserPuzzleBoard(
            @SerialName("reviewDate")
            val reviewDate: String?,
            @SerialName("reviewMemberPercent")
            val reviewMemberPercent: String?,
            @SerialName("puzzleAssetName")
            val puzzleAssetName: String,
        )

        fun toPuzzleBoard() = teamPuzzleBoard.map { board ->
            TeamPuzzleBoard(
                board.reviewDate ?: "null",
                board.reviewMemberPercent ?: "null",
                board.puzzleAssetName,
            )
        }
    }
}
