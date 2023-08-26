package com.cmc.curtaincall.core.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cmc.curtaincall.domain.model.show.ShowSearchWordModel

@Entity
data class ShowSearchWordEntity(
    @PrimaryKey val word: String,
    val searchAt: Long
) {
    fun toModel() = ShowSearchWordModel(
        searchAt = searchAt,
        word = word
    )
}
