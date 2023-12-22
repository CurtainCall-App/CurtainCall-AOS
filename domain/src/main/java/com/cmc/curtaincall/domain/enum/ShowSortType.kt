package com.cmc.curtaincall.domain.enum

enum class ShowSortType(
    val value: String,
    val code: String
) {
    REVIEW_GRADE("별점순", "reviewGradeAvg,desc"),
    END_DATE("종료 임박순", "endDate"),
    NAME("가나다순", "name")
}
