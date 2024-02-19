package com.cmc.curtaincall.domain.type

enum class ReviewSortType(
    val label: String
) {
    RECENTLY("최신순"),
    GRADE("별점순"),
    FAVORITE("좋아요순")
}
