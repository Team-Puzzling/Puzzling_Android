package com.puzzling.puzzlingaos.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class KakaoToken(
    val accessToken: String? = null,
)
