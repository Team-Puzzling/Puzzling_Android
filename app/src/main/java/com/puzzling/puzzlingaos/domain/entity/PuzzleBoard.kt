package com.puzzling.puzzlingaos.domain.entity

import androidx.annotation.DrawableRes

data class PuzzleBoard(
    val date: String,
    @DrawableRes val boardImage: Int,
)
