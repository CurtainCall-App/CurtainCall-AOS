package com.cmc.curtaincall.domain.enums

enum class ReviewSortType(
    val label: String,
    val code: String,
) {
    RECENTLY("최신순", "createdAt,desc"),
    GRADE("별점순", "grade,desc"),
    FAVORITE("좋아요순", "likeCount,desc")
}
