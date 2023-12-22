package com.cmc.curtaincall.feature.show.search

import com.cmc.curtaincall.core.base.BaseState
import com.cmc.curtaincall.domain.enum.ShowGenreType
import com.cmc.curtaincall.domain.enum.ShowSortType

data class ShowSearchUiState(
    val isActiveSearch: Boolean = false,
    val isDoneSearch: Boolean = false,
    val queryString: String = "",
    val genre: ShowGenreType = ShowGenreType.PLAY,
    val sortType: ShowSortType = ShowSortType.REVIEW_GRADE
) : BaseState