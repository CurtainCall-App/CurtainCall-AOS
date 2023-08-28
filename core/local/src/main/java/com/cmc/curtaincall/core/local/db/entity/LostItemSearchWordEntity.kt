package com.cmc.curtaincall.core.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cmc.curtaincall.domain.model.lostItem.LostItemSearchWordModel

@Entity
data class LostItemSearchWordEntity(
    @PrimaryKey val word: String,
    val searchAt: Long
) {
    fun toModel() = LostItemSearchWordModel(
        searchAt = searchAt,
        word = word
    )
}
