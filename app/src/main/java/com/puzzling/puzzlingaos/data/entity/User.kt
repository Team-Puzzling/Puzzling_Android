package com.puzzling.puzzlingaos.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val memberId: Int? = null,
    val name: String? = null,
)
