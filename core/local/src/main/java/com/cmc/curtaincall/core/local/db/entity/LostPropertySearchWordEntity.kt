package com.cmc.curtaincall.core.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cmc.curtaincall.domain.model.lostproperty.LostPropertySearchWordModel

@Entity
data class LostPropertySearchWordEntity(
    @PrimaryKey val word: String,
    val searchAt: Long
) {
    fun toModel() = LostPropertySearchWordModel(
        searchAt = searchAt,
        word = word
    )
}
