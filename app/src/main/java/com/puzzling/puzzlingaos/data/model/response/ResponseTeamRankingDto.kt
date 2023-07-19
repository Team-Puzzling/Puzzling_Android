package com.puzzling.puzzlingaos.data.model.response

import com.puzzling.puzzlingaos.domain.entity.TeamRanking
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseTeamRankingDto(
    @SerialName("status")
    val status: Int,
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: List<TeamRankingData>,
) {
    @Serializable
    data class TeamRankingData(
        @SerialName("memberRank")
        val memberRank: Int,
        @SerialName("memberNickname")
        val memberNickname: String,
        @SerialName("memberRole")
        val memberRole: String,
        @SerialName("memberPuzzleCount")
        val memberPuzzleCount: Int,
    )

    fun toTeamRanking() = data.map { ranking ->
        TeamRanking(
            memberRank = ranking.memberRank,
            memberNickname = ranking.memberNickname,
            memberRole = ranking.memberRole,
            memberPuzzleCount = ranking.memberPuzzleCount,

        )
    }
}
