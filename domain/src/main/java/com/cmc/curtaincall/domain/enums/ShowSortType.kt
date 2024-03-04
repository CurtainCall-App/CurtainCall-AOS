package com.cmc.curtaincall.domain.enums

enum class ShowSortType(
    val value: String,
    val code: String
) {
    POPULAR("인기순", "reviewGradeAvg,desc"),
    REVIEW_GRADE("별점순", "reviewGradeAvg,desc"),
    END_DATE("종료 임박순", "endDate"),
    NAME("가나다순", "name")
}
