package com.puzzling.puzzlingaos.domain.entity

data class DetailRetro(
    val reviewId: Int?,
    val reviewDay: String,
    val reviewDate: String,
    val reviewTemplateId: Int?,
    val content: List<Content>?,
) {
    data class Content(
        val title: String,
        val content: String,
    )
}
