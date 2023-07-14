package com.puzzling.puzzlingaos.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamRanking(
    @SerialName("memberRank")
    val memberRank: Int,
    @SerialName("memberNickname")
    val memberNickname: String,
    @SerialName("memberRole")
    val memberRole: String,
    @SerialName("memberPuzzleCount")
    val memberPuzzleCount: Int,

)

// data class TeamRanking(
//    val memberRank: Int,
//    val memberNickname: String,
//    val memberRole: String,
//    val memberPuzzleCount: Int,
//
// )
