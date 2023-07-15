package com.puzzling.puzzlingaos.domain.entity

data class TeamRetrospectList(
    var type: Int,
    var titleText: String?,
    var memberNickname: String?,
    var memberRole: String?,
) {
    companion object {
        const val DO_RETROSPECT_TEXT = 0
        const val DONT_RETROSPECT_TEXT = 1
        const val RETROSPECT_LIST = 2
    }
}
