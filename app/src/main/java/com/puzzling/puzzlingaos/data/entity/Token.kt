package com.puzzling.puzzlingaos.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class Token(
    val accessToken: String? = null,
    val refreshToken: String? = null,
)
